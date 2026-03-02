package ssp.lib.printqueue.service;

import avicit.platform6.commons.utils.ComUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssp.ddd.support.dao.BasicDao;
import ssp.lib.docEntity.service.SspBaseDocService;
import ssp.lib.printqueue.dao.SspPrintQueueDao;
import ssp.lib.printqueue.entity.AppPrintSets;
import ssp.lib.printqueue.entity.AppdataPrintQueue;
import ssp.lib.printqueue.entity.AppdataPrintTasks;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.exists;

@Service
public class SspPrintQueueService {
    @Autowired
    private SspPrintQueueDao sspPrintQueueDao;

    @Autowired
    private SspBaseDocService docService;

    @Resource(name = "repository")
    private BasicDao basicDao;


    public Map<String,Object> toRQReportPage(Map<String,Object> param) {
        //注意map 的key不能改变 即必定含有 view 和 request
        Map<String, Object> map = new HashMap();
        Map<String, Object> requestMap = new HashMap();
        Map<String, Object> data = new HashMap();
        //获取最大密级
        map.put("view","ssp/lib/sspShowReport");
        //设置需要加载的Action JS文件 此数组中的每个值将会当作路径进行对应js的加载
        requestMap.put("moduleJs",new String[]{""});
        //设置Jsp title
        requestMap.put("title","打印页面");
        //设置需要加载的config JS文件 此文件保存工具生成的config
        requestMap.put("configPath","");
        requestMap.put("requestData", param.get("data"));
        map.put("request",requestMap);
        return map;
    }

    /**
     * 创建流程表单打印任务并创建打印队列
     * @param formCode
     * @param docId
     * @param writerId
     */
    public void createDocPrintTask(String formCode,String docId,String writerId) {
        Map<String,Object> printInfo = sspPrintQueueDao.getFormSetPrintInfo(formCode);
        String printTemplate = (String) printInfo.get("printTemplate");
        String printOption = (String) printInfo.get("printOption");
        createPrintTask(printTemplate,"auto",docId,"flowDoc",writerId);
        //根据 appsys.APP_FORM_SETS.PRINT_OPTION 把其它需要的人员加入到打印队列中
    }

    /**
     * 创建业务表单打印任务并创建打印队列
     * @param formCode
     * @param docId
     * @param writerId
     */
    public void createBizDocPrintTask(String formCode,String docId,String writerId) {
        Map<String,Object> printInfo = sspPrintQueueDao.getFormSetPrintInfo(formCode);
        String printTemplate = (String) printInfo.get("printTemplate");
        String printOption = (String) printInfo.get("printOption");
        createPrintTask(printTemplate,"auto",docId,"bizDoc",writerId);
        //根据 appsys.APP_FORM_SETS.PRINT_OPTION 把其它需要的人员加入到打印队列中
    }


    /**
     * 创建通用的打印任务并创建打印队列
     * @param templateId 打印模板id
     * @param dataId 业务数据Id
     * @param dataSrc bizDoc/flowDoc
     * @param userId 指定人员
     */
    public void createPrintTask(String templateId,String taskSrc,String dataId,String dataSrc,String userId) {
        String oldTaskId = getDocPrintTask(dataId,dataSrc);
        if (oldTaskId != null) { //如果此业务数据已创建打印任务，则删除之前创建的任务，生成新任务
            AppdataPrintTasks oldAppdataPrintTasks = new AppdataPrintTasks();
            oldAppdataPrintTasks.setId(oldTaskId);
            basicDao.deleteAll(oldAppdataPrintTasks);
        }
        AppdataPrintTasks appdataPrintTasks = new AppdataPrintTasks();
        AppdataPrintQueue appdataPrintQueue = new AppdataPrintQueue();
        List<AppdataPrintQueue> printQueueList = new ArrayList<>();

        taskSrc = (taskSrc == null || "".equals(taskSrc))?"assign":taskSrc;
        appdataPrintTasks.setId(ComUtil.getId());
        appdataPrintTasks.setDataId(dataId);
        appdataPrintTasks.setDataSrc(dataSrc);
        appdataPrintTasks.setPrintTmplate(templateId);
        appdataPrintTasks.setTaskSrc(taskSrc);
        appdataPrintTasks.setRecFlag("N");

        appdataPrintQueue.setId(ComUtil.getId());
        appdataPrintQueue.setTaskId(appdataPrintTasks.getId());
        appdataPrintQueue.setPrinterId(userId);
        appdataPrintQueue.setDataId(dataId);
        appdataPrintQueue.setRecFlag("N");

        printQueueList.add(appdataPrintQueue);
        appdataPrintTasks.setPrintQueueList(printQueueList);
        basicDao.save(appdataPrintTasks);
    }

    /**
     * 获取指定业务数据id所对应的打印任务id
     * @param dataId 业务数据Id
     * @param dataSrc bizDoc/flowDoc
     * @return
     */
    public String getDocPrintTask(String dataId,String dataSrc) {
        return sspPrintQueueDao.getTaskIdByDocInfo(dataId,dataSrc);
    }

    /**
     * 将指定用户加入到打印队列中
     * @param dataId 业务数据Id
     * @param dataSrc bizDoc/flowDoc
     * @param userId 指定人员
     */
    public void appendQueue(String dataId,String dataSrc,String userId) {
        String taskId = getDocPrintTask(dataId,dataSrc);
        AppdataPrintQueue appdataPrintQueue = new AppdataPrintQueue();
        appdataPrintQueue.setId(ComUtil.getId());
        appdataPrintQueue.setTaskId(taskId);
        appdataPrintQueue.setPrinterId(userId);
        appdataPrintQueue.setDataId(dataId);
        appdataPrintQueue.setRecFlag("N");
        basicDao.save(appdataPrintQueue);
    }

    /**
     * 将指定用户从打印队列中移除
     * @param dataId 业务数据Id
     * @param dataSrc bizDoc/flowDoc
     * @param userId 指定人员
     */
    public void removeFromQueue(String dataId,String dataSrc,String userId) {
        Map<String,Object> param = new HashMap<>();
        param.put("dataId",dataId);
        param.put("dataSrc",dataSrc);
        param.put("printerId",userId);
        sspPrintQueueDao.removeFromQueue(param);
    }

    /**
     * 获取指定业务数据id及用户所允许打印的任务设置
     * @param param {String dataId,String dataSrc,String userId}
     */
    public Map<String,Object> getPrintTaskSets(Map<String,Object> param) {
        String dataId = (String) param.get("dataId");
        String dataSrc = (String) param.get("dataSrc");
        String userId = (String) param.get("userId");
        Map<String,Object> result = new HashMap<>();
        AppdataPrintTasks appdataPrintTasks = new AppdataPrintTasks();
        appdataPrintTasks.setDataId(dataId);
        appdataPrintTasks.setDataSrc(dataSrc);
        appdataPrintTasks.setRecRemove(-1);
        List<AppdataPrintTasks> list = basicDao.loadAll(appdataPrintTasks);
        if (list == null || list.size() == 0) {
            return result;
        }
        appdataPrintTasks = list.get(0);
        List<AppdataPrintQueue> printQueueList = appdataPrintTasks.getPrintQueueList();
        for (AppdataPrintQueue appdataPrintQueue : printQueueList) {
            if (appdataPrintQueue.getPrinterId().equals(userId)) {
                AppPrintSets appPrintSets = new AppPrintSets();
                appPrintSets.setId(appdataPrintTasks.getPrintTmplate());
                appPrintSets = basicDao.loadAll(appPrintSets).get(0);
                result = JSON.parseObject(JSON.toJSONString(appPrintSets));
                return result;
            }
        }

        return result;
    }

    /**
     * 根据 templateName(打印模板名称), ver(版本)，查询 appsys.APP_PRINT_SETS，获取对应打印设置
     * @param templateName 打印模板名称
     * @param ver 版本
     * @return
     */
    public Map<String,Object> getPrintSets(String templateName,String ver) {
        AppPrintSets appPrintSets = new AppPrintSets();
        ver = ver == null ? "1" : ver;
        appPrintSets.setTemplateName(templateName);
        appPrintSets.setTemplateVer(ver);
        appPrintSets = basicDao.loadAll(appPrintSets).get(0);
        Map<String,Object> result = JSON.parseObject(JSON.toJSONString(appPrintSets));
        return result;
    }
}
