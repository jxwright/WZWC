package avicit.cmjt.task.service;


import avicit.cmjt.cmjtcontract.dao.CmjtContractDAO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtpayreceinfo.dao.CmjtPayReceInfoDAO;
import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.cmjt.cmjtsigninfo.dao.CmjtSignInfoDAO;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsubjectbase.dao.CmjtSubjectBaseDAO;
import avicit.cmjt.cmjtsubjectbase.dto.CmjtSubjectBaseDTO;
import avicit.cmjt.cmjtsubjectinfo.dao.CmjtSubjectInfoDAO;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.cmjt.task.dao.CmjtSyncStatusDAO;
import avicit.cmjt.task.dto.CmjtSyncStatusDTO;
import avicit.cmjt.task.dto.SyncResultDTO;
import avicit.cmjt.utils.ContractService;
import avicit.cmjt.utils.SwfUploadUtils;
import avicit.cust.domain.customer.entity.Cust;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.modules.system.sysfileupload.domain.SysFileUpload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CmSyncService {

    @Autowired
    private CmjtSyncStatusDAO cmjtSyncStatusDAO;

    @Autowired
    private CmjtContractDAO cmjtContractDAO;

    @Autowired
    private CmjtPayReceInfoDAO cmjtPayReceInfoDAO;

    @Autowired
    private CmjtSubjectInfoDAO cmjtSubjectInfoDAO;

    @Autowired
    private CmjtSignInfoDAO cmjtSignInfoDAO;

    @Autowired
    private CmjtSubjectBaseDAO cmjtSubjectBaseDAO;

    @Transactional
    public SyncResultDTO syncCmLedgerData(String systemName) {
        CmjtSyncStatusDTO status = cmjtSyncStatusDAO.findBySystemName(systemName);
        if (status == null) {
            status = initSyncStatus(systemName);
        }

        try {
            // 调用远程接口获取数据
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String,String> params = new HashMap<>();
            Date syncTime = status.getLastSyncTime();
            //如果未获取到当前数据最后一次同步时间，则为第一次取数，获取所有的数据
            String creationDateBegin = "";
            if(syncTime == null){
                creationDateBegin = "2000-01-01";
            }else{
                creationDateBegin = dateFormat.format(syncTime);
            }
            params.put("creationDateBegin", creationDateBegin);
            params.put("creationDateEnd", dateFormat.format(date));
            params.put("orgCode","131320");
            JSONObject cms = ContractService.queryContracts(params);
            String retCode = cms.getString("retCode");
            if("200".equals(retCode)) {
                    List<CmjtContractDTO> newData = cms.getJSONArray("responseBody").toJavaList(CmjtContractDTO.class);
                    // 保存台账数据
                    saveLedgerData(newData);
                    // 更新同步状态
                    updateSyncStatusSuccess(status);
                    return new SyncResultDTO(true, "同步成功", newData.size());
            } else {
                throw new RuntimeException("远程接口调用失败: " + cms.getString("retMsg"));
            }
        } catch (Exception e) {
            return handleSyncFailure(status, e);
        }
    }

    private void saveLedgerData(List<CmjtContractDTO> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        StringBuilder errorLog = new StringBuilder(); // 用于收集错误信息
        String logFilePath = "D:/error_logs_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";

        ExecutorService executor = Executors.newFixedThreadPool(10);
        data.forEach(item -> {
            try{
                // 新增合同签约信息
                List<CmjtSignInfoDTO> signInfoList = item.getCmSignInfoList();
                signInfoList.forEach(item1 ->{
                    cmjtSignInfoDAO.updateMerge(item1);
                    Cust cust = new Cust();

                    cust.setCode(item1.getUnifiedSocialCreditCode());
                    cust.setName(item1.getSupplierName());
                    cust.setId(ComUtil.getId());
                    cust.setCreatedBy("1");
                    cust.setCreationDate(new Date());
                    cust.setLastUpdateDate(new Date());
                    cust.setVersion(0l);
                    cust.setLastUpdateIp("1");
                    cust.setLastUpdatedBy("1");
                    //PojoUtil.setSysProperties(cust, PlatformConstant.OpType.insert);
                    cmjtSyncStatusDAO.updateCustMerge(cust);
                    executor.submit(()->{
                        try {
                            updateFmpCust(cust);
                        }catch (Exception e){

                        }
                    });
                });
//            if (CollectionUtils.isNotEmpty(signInfoList)) {
//                cmjtSignInfoDAO.insertBatch(signInfoList);
//            }

                //新增合同标的物信息
                List<CmjtSubjectInfoDTO> subjectInfoList = item.getCmSubject064BdList();
//            if (CollectionUtils.isNotEmpty(subjectInfoList)) {
//                cmjtSubjectInfoDAO.insertBatch(subjectInfoList);
//            }
                subjectInfoList.forEach(item2 ->{
                    cmjtSubjectInfoDAO.updateMerge(item2);
                });

                // 新增合同收付款计划
                List<CmjtPayReceInfoDTO> payReceInfoList = item.getCmSubject102BdList();
//            if (CollectionUtils.isNotEmpty(payReceInfoList)) {
//                cmjtPayReceInfoDAO.insertBatch(payReceInfoList);
//            }
                payReceInfoList.forEach(item3 ->{
                    cmjtPayReceInfoDAO.updateMerge(item3);
                });
//            PojoUtil.setSysProperties(item, PlatformConstant.OpType.insert);
                //新增合同信息
                cmjtContractDAO.updateMerge(item);
            }
            catch (Exception e){
                errorLog.append("合同 ").append(item.getContractNo()).append(" 更新失败: ").append(e.getMessage()).append(System.lineSeparator());
            }
        });
        executor.shutdown();
        while (!executor.isTerminated()) {
            // 等待所有任务完成
        }
        // 将错误信息写入文件
        if (errorLog.length() > 0) {
            try (FileWriter writer = new FileWriter(logFilePath, true)) {
                writer.write(errorLog.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void updateSyncStatusSuccess(CmjtSyncStatusDTO status) {

        status.setLastSyncTime(new Date());
        status.setLastSuccessTime(new Date());
        status.setRetryCount(0);
        status.setLastError(null);
        status.setModifyTime(new Date());
        cmjtSyncStatusDAO.update(status);
    }

    private SyncResultDTO handleSyncFailure(CmjtSyncStatusDTO status, Exception e) {
        status.setRetryCount(status.getRetryCount() + 1);
        status.setLastError(e.getMessage());
        status.setModifyTime(new Date());
        cmjtSyncStatusDAO.update(status);
        return new SyncResultDTO(false, "同步失败: " + e.getMessage(), 0);
    }

    private CmjtSyncStatusDTO initSyncStatus(String systemName) {
        CmjtSyncStatusDTO status = new CmjtSyncStatusDTO();
        status.setSystemName(systemName);
        status.setCreateTime(new Date());
        status.setModifyTime(new Date());
        status.setRetryCount(0);

        cmjtSyncStatusDAO.insert(status);
        return status;
    }

    @Transactional
    public SyncResultDTO syncCmjtSubjectBaseData(String systemName) {
        CmjtSyncStatusDTO status = cmjtSyncStatusDAO.findBySystemName(systemName);
        if (status == null) {
            status = initSyncStatus(systemName);
        }

        try {
            //获取本地未推送到集团的数据
            CmjtSubjectBaseDTO queryDTO = new CmjtSubjectBaseDTO();
            queryDTO.setIsTrans("0");
            List<CmjtSubjectBaseDTO> unPushedList = cmjtSubjectBaseDAO.searchCmjtSubjectBase(queryDTO);

            if (CollectionUtils.isEmpty(unPushedList)) {
                return new SyncResultDTO(true, "同步成功", unPushedList.size());
            }

            // 将未推送到集团的数据进行推送
            unPushedList.parallelStream().forEach(subject -> {
                try {
                    processUnpushedSubject(subject);
                } catch (Exception e) {
                    //记录推送失败原因
                    updateSubjectAfterFailurePush(subject, e);
                }
            });
            // 更新同步状态
            updateSyncStatusSuccess(status);
            return new SyncResultDTO(true, "同步成功", unPushedList.size());
        } catch (Exception e) {
            return handleSyncFailure(status, e);
        }
    }

    /**
     * 将未推送到集团的标的信息调用相关接口进行推送
     * @param subject
     */
    private void processUnpushedSubject(CmjtSubjectBaseDTO subject) {
        String operationType = subject.getOperationType();
        if (StringUtils.isEmpty(operationType)) {
            return;
        }

        JSONObject response = null;
        //判断数据未推送成功的原因，调用相关的接口
        switch (operationType) {
            case "insert":
                response = ContractService.addSubject(dtoConvertToParams(subject,"insert"));
                break;
            case "update":
                response = ContractService.updateSubject(dtoConvertToParams(subject,"update"));
                break;
            default:
                return;
        }
        String retCode = response.getString("retCode");
        if("200".equals(retCode))
            updateSubjectAfterSuccessfulPush(subject, response, operationType);
        else
            updateSubjectAfterFailurePush(subject, response);
    }

    /**
     * 推送成功后，更新本地的标的信息
     * @param subject
     * @param response
     */
    private void updateSubjectAfterSuccessfulPush(CmjtSubjectBaseDTO subject, JSONObject response, String operationType) {
        String id = "";
        switch (operationType) {
            case "insert":
                id = response.getString("responseBody");
                break;
            case "update":
                //更新操作的集团ID肯定不为空
                id =subject.getJtId();
                break;
            default:
                return;
        }
        subject.setJtId(id);
        subject.setIsTrans("1");
        subject.setErrorInfo(""); //清空错误信息
        PojoUtil.setSysProperties(subject, PlatformConstant.OpType.update);
        cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(subject);
    }

    /**
     * 推送失败后，记录失败原因
     * @param subject
     * @param e
     */
    private void updateSubjectAfterFailurePush(CmjtSubjectBaseDTO subject, Exception e) {
        subject.setIsTrans("0");
        subject.setErrorInfo(e.getMessage());
        PojoUtil.setSysProperties(subject, PlatformConstant.OpType.update);
        cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(subject);
    }
    private void updateSubjectAfterFailurePush(CmjtSubjectBaseDTO subject, JSONObject response) {
        subject.setIsTrans("0");
        subject.setErrorInfo(response.getString("errorDesc")+response.getString("message"));
        PojoUtil.setSysProperties(subject, PlatformConstant.OpType.update);
        cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(subject);
    }
    /**
     * 将dto转换成标的增加，标的更新需要的参数
     * @param dto
     */
    private Map<String, Object> dtoConvertToParams(CmjtSubjectBaseDTO dto, String operationType) {
        //新增和更新通用参数
        Map<String, Object> params = new HashMap<>();
        params.put("businessId",dto.getBusinessId());
        params.put("deliverDate",dto.getDeliverDate());
        params.put("increDecreRate",dto.getIncreDecreRate());
        params.put("lastUpdateDate",dto.getLastUpdateDate());
        params.put("planType",dto.getPlanType());
        params.put("specsType",dto.getSpecsType());
        params.put("subjectName",dto.getSubjectName());
        params.put("subjectNum",dto.getSubjectNum());
        params.put("subjectPrice",dto.getSubjectPrice());
        params.put("subjectTotalAmount",dto.getSubjectTotalAmount());
        params.put("taxRate",dto.getTaxRate());
        params.put("unit",dto.getUnit());
        params.put("taskNo",dto.getTaskNo());
        params.put("taskType",dto.getTaskType());
        //不同的参数
        switch (operationType) {
            case "insert":
                break;
            case "update":
                params.put("id","1");
                break;
            default:
                return params;
        }
        return params;
    }

    private  void updateFmpCust(Cust cust){
        try{
            String url = "http://129.1.119.137:10001/api/v1/common/updateCust";
            URI uri = new URI(url);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");//AEF56B8AE355963D6D75CD8CE4CB950F
            connection.setDoOutput(true);
            connection.setRequestProperty("Sign", "AEF56B8AE355963D6D75CD8CE4CB950F");
            connection.setRequestProperty("Basic", "avicit2015");
            JSONObject j=new JSONObject();
            j.put("code",cust.getCode());
            j.put("name",cust.getName());
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = j.toJSONString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            // 获取响应状态码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应内容
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    System.out.println("updateFmpCust: " + response.toString());

                }
            } else {
                System.err.println("updateFmpCustfail:"  + responseCode);
            }
            // 关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void syncCmjtOrderData(String cmjtOrder) {
        CmjtSyncStatusDTO status = cmjtSyncStatusDAO.findBySystemName(cmjtOrder);
        if (status == null) {
            status = initSyncStatus(cmjtOrder);
        }
        try {
            // 调用远程接口获取数据
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Map<String,Object> params = new HashMap<>();
            Date syncTime = status.getLastSyncTime();
            //如果未获取到当前数据最后一次同步时间，则为第一次取数，获取所有的数据
            String creationDateBegin = "";
            if(syncTime == null){
                creationDateBegin = "2000-01-01";
            }else{
                creationDateBegin = dateFormat.format(syncTime);
            }
            params.put("lastUpdateDateBegin", creationDateBegin);
            params.put("lastUpdateDateEnd", dateFormat.format(date));
            params.put("orgIdentity","131320");
            JSONObject cms = ContractService.queryCmjtOrder(params);
            String retCode = cms.getString("retCode");
            if("200".equals(retCode)) {
                JSONArray newData = cms.getJSONArray("responseBody");
                // 保存台账数据
                saveCmjtOrderData(newData);
                // 更新同步状态
                updateSyncStatusSuccess(status);
            } else {
                throw new RuntimeException("远程接口调用失败: " + cms.getString("retMsg"));
            }
        } catch (Exception e) {
            handleSyncFailure(status, e);
        }
    }

    private void saveCmjtOrderData(JSONArray data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        data.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            String contractCode = obj.getString("contractCode");
            String contractId = obj.getString("contractId");
            CmjtContractDTO contract = cmjtContractDAO.get(contractId);
            cmjtContractDAO.updateMerge(contract);//更新合同信息，自动同步到财务系统
            if (contract != null) {
                JSONArray cmOrderPayment = obj.getJSONArray("cmOrderPaymentDTOList");
                JSONArray cmOrderSubject = obj.getJSONArray("cmOrderSubjectDTOList");
                // 处理付款信息
                if (cmOrderPayment != null && !cmOrderPayment.isEmpty()) {
                    cmOrderPayment.forEach(pay -> {
                        CmjtPayReceInfoDTO payInfo = new CmjtPayReceInfoDTO();
                        JSONObject payJson = (JSONObject) pay;
                        payInfo.setZbid(contractId);
                        payInfo.setId(payJson.getString("id"));
                        payInfo.setCr00324001(payJson.getBigDecimal("amountMoney"));//金额
                        payInfo.setCr00321001(payJson.getString("natureOfPayment"));//款项性质
                        payInfo.setCr00322001(payJson.getString("settlementCondition"));//结算条件
                        payInfo.setCr00323001(payJson.getString("planPayDate"));//付款方式
                        payInfo.setCr00325001(payJson.getBigDecimal("taxRate"));//税率
                        payInfo.setCr00326001(payJson.getBigDecimal("taxAmount")); //税额
                        payInfo.setCr00327001(payJson.getString("currency"));//币种
                        payInfo.setCr00327001Zhmc(payJson.getString("currencyZhmc"));//币种中文名称
                        payInfo.setCr00328001(payJson.getBigDecimal("exchangeRate"));//汇率
                        payInfo.setCr00329001(payJson.getBigDecimal("rmbAmountMoney"));//人民币金额
                        payInfo.setCreationDate(payJson.getDate("creationDate"));//创建时间
                        payInfo.setCreatedBy(payJson.getString("createdBy"));
                        payInfo.setLastUpdateDate(payJson.getDate("lastUpdateDate"));
                        payInfo.setLastUpdatedBy(payJson.getString("lastUpdatedBy"));
                        payInfo.setLastUpdateIp(payJson.getString("lastUpdateIp"));
                        cmjtPayReceInfoDAO.updateMerge(payInfo);
                    });
                }
                // 处理标的信息
                if (cmOrderSubject != null && !cmOrderSubject.isEmpty()) {
                    cmOrderSubject.forEach(sub -> {
                        CmjtSubjectInfoDTO subjectInfo = new CmjtSubjectInfoDTO();
                        JSONObject subJson = (JSONObject) sub;
                        subjectInfo.setId(subJson.getString("id"));
                        subjectInfo.setZbid(contractId);
                        subjectInfo.setCr00312001(subJson.getString("subjectName"));//标的名称
                        subjectInfo.setCr00315001(subJson.getBigDecimal("signNumber"));//标的数量
                        subjectInfo.setCr00316001(subJson.getBigDecimal("price"));//单价
                        subjectInfo.setCr00317001(subJson.getBigDecimal("totalMoney"));//总金额
                        subjectInfo.setCr00318001(subJson.getBigDecimal("taxRate"));//税率
                        subjectInfo.setCr00314001(subJson.getString("unitOfMeasurement"));//单位
                        subjectInfo.setCr00313001(subJson.getString("specificationsModel"));//规格型号
                        subjectInfo.setCr00319001(subJson.getString("cmAgreePayDate"));//交付时间
                        subjectInfo.setCreationDate(subJson.getDate("creationDate"));//创建时间
                        subjectInfo.setCreatedBy(subJson.getString("createdBy"));
                        subjectInfo.setLastUpdateDate(subJson.getDate("lastUpdateDate"));
                        subjectInfo.setLastUpdatedBy(subJson.getString("lastUpdatedBy"));
                        subjectInfo.setLastUpdateIp(subJson.getString("lastUpdateIp"));
                        cmjtSubjectInfoDAO.updateMerge(subjectInfo);
                    });
                }
            }
        });
    }

    /**
     * 根据合同id获取附件并保存到本地和数据库
     */
    public void fetchAndSaveAttachments(String contractId) throws Exception {
        // 1. 调用接口
        Map<String,Object> params = new HashMap<>();
        params.put("id",contractId);
        JSONObject cms = ContractService.queryCmjtAttachment(params);

        // 2. 解析返回值
        String retCode = cms.getString("retCode");
        if("200".equals(retCode)) {
            JSONArray newData = cms.getJSONArray("responseBody");
            // 递归解析附件
            newData.forEach(item -> {
                JSONObject node = (JSONObject) item;
                try {
                    parseAttachmentNode(node, contractId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            // 更新同步状态
        } else {
            throw new RuntimeException("远程接口调用失败: " + cms.getString("retMsg"));
        }

    }

    /**
     * 递归解析附件节点
     */
    private void parseAttachmentNode(JSONObject node, String contractId) throws Exception {
        // cmFileList
        JSONArray cmFileList = node.getJSONArray("cmFileList");
        if( cmFileList != null && !cmFileList.isEmpty()) {
            for (Object fileObj : cmFileList) {
                JSONObject fileNode = (JSONObject) fileObj;
                saveFile(fileNode, contractId);
            }
        }
        // childList
        JSONArray childList = node.getJSONArray("childList");
        if( childList != null && !childList.isEmpty()) {
            for (Object childNode : childList) {
                parseAttachmentNode((JSONObject) childNode, contractId);
            }
        }
    }

    /**
     * 保存单个附件
     */
    private void saveFile(JSONObject fileNode, String contractId) throws Exception {
        String fileName = fileNode.getString("fileName");
        File file = new File(fileName);
        String fileBody = fileNode.getString("fileBody");
        byte[] bytes = Base64.getDecoder().decode(fileBody);
        // Base64解码并写入磁盘
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
        // 保存数据库记录
        SwfUploadUtils.save2Disk(file, contractId, "cmjt_contract", "1");
    }


}
