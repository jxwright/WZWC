package avicit.cmjt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContractService {

    private static  final String tokenUrl="http://10.110.32.81:30003";
    /**
     * 任务发布接口
     * @param params 任务信息
     * @return 接口响应
     */
    public static JSONObject publishTask(Map<String,Object> params) {
        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmosintegrationmains/execute/*/v1", params));
    }

    /**
     * 标的物增加接口
     * @param params 标的物信息
     * @return 接口响应
     */
    public static JSONObject addSubject(Map<String,Object> params) {
        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmsubject/save/v1", params));
    }

    /**
     * 标的物删除接口
     * subjectId 标的物ID
     * @return 接口响应
     */
    public static JSONObject deleteSubject(String subjectId) {
        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", subjectId);
//        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmsubject/delete-by-id/"+subjectId+"/v1", pathVariables));
        return  JSON.parseObject(ContractSystemClient.deleteJson(tokenUrl+"/api/integration/cmsubject/delete-by-id/"+subjectId+"/v1", pathVariables));
    }

    /**
     * 标的物更新接口
     * @param params 标的物信息
     * @return 接口响应
     */
    public static JSONObject updateSubject(Map<String, Object> params) {
        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmsubject/update-all/v1", params));
    }

    /**
     * 标的物查询接口
     * @param params 查询参数
     * @return 接口响应
     */
    public static JSONObject querySubjects(Map<String,Object> params) {
        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmsubject/find-list/v1", params));
    }

    /**
     * 合同交付履约反馈接口
     * @param params 履约反馈信息
     * @return 接口响应
     */
    public static JSONObject submitFeedback(Map<String,Object> params) {
        return  JSON.parseObject(ContractSystemClient.postJson(tokenUrl+"/api/integration/cmhtfk/save/v1", params));
    }

    /**
     * 合同台账查询接口
     * @param params 查询参数
     * @return 接口响应
     */
    public static JSONObject queryContracts(Map<String,String> params) {
        return  JSON.parseObject(ContractSystemClient.postByUrlParam(tokenUrl+"/api/integration/cmxfcontract/find-contract-list/v1", params));
    }

    /**
     * 合同订单查询接口
     * @param params 查询参数
     * @return 接口响应
     */
    public static JSONObject queryCmjtOrder(Map<String,Object> params) {
        return JSON.parseObject(ContractSystemClient.postJson(tokenUrl + "/api/cmcontractanalyse/cmcontractordervs/find-list/v1", params));
    }


    public static JSONObject queryCmjtAttachment(Map<String,Object> params) {
        return JSON.parseObject(ContractSystemClient.postJson(tokenUrl + "/api/integration/cmattachment/get-cm-attachment-by-id/v1", params));
    }

}
