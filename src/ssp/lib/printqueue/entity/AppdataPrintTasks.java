package ssp.lib.printqueue.entity;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import ssp.ddd.support.entity.Entity;

import javax.persistence.Id;
import java.util.List;

public class AppdataPrintTasks extends Entity<String> {
    
    /**
    * 表主键({GUID}))
    */    
    @Id
    @LogField
    @FieldRemark(column="id",field="ID",name="表主键({GUID}))")
    private String id;
    
    /**
    * 业务数据记录ID，根据DATA_SRC关联不同的数据表
    */    
    @LogField
    @FieldRemark(column="dataId",field="DATA_ID",name="业务数据记录ID，根据DATA_SRC关联不同的数据表")
    private String dataId;
        
    /**
    * 业务数据源类型 flowDoc-FLOW_DOC bizDoc-BIZ_DOC report
    */    
    @LogField
    @FieldRemark(column="dataSrc",field="DATA_SRC",name="业务数据源类型 flowDoc-FLOW_DOC bizDoc-BIZ_DOC report")
    private String dataSrc;
        
    /**
    * 打印任务创建方式 auto-自动创建 assign-指派 plan-依据计划产生
    */    
    @LogField
    @FieldRemark(column="taskSrc",field="TASK_SRC",name="打印任务创建方式 auto-自动创建 assign-指派 plan-依据计划产生")
    private String taskSrc;
        
    /**
    * 打印模板ID, 关联 appsys.APP_PRINT_SETS.id
    */    
    @LogField
    @FieldRemark(column="printTmplate",field="PRINT_TMPLATE",name="打印模板ID, 关联 appsys.APP_PRINT_SETS.id")
    private String printTmplate;
        
    /**
    * 打印任务的分组 -1 没有分组 其它值，对应需完成一组打印时的分组ID
    */    
    @LogField
    @FieldRemark(column="groupId",field="GROUP_ID",name="打印任务的分组 -1 没有分组 其它值，对应需完成一组打印时的分组ID")
    private String groupId;
        
    /**
    * 计划打印份数，根据队列设置，每个队列记录算一份
    */    
    @LogField
    @FieldRemark(column="planNum",field="PLAN_NUM",name="计划打印份数，根据队列设置，每个队列记录算一份")
    private Double planNum;
        
    /**
    * 已打印数量，当已打印数量与PLAN_NUM相同时，STATE=done
    */    
    @LogField
    @FieldRemark(column="printedCount",field="PRINTED_COUNT",name="已打印数量，当已打印数量与PLAN_NUM相同时，STATE=done")
    private Double printedCount;
        
    /**
    * 任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停
    */    
    @LogField
    @FieldRemark(column="state",field="STATE",name="任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停")
    private String state;
                                                        
    /**
    * 删除标志。-1:未删除
    */    
    @LogField
    @FieldRemark(column="recRemove",field="REC_REMOVE",name="删除标志。-1:未删除")
    private Integer recRemove;

    private List<AppdataPrintQueue> printQueueList;

    /**
    * 获取表主键({GUID}))
    */    
    public String getId(){
        return id;
    }

    /**
    * 设置表主键({GUID}))
    * @param id 表主键({GUID}))
    */    
    public void setId(String id){
        this.id = id;
    }
    
    /**
    * 获取业务数据记录ID，根据DATA_SRC关联不同的数据表
    */    
    public String getDataId(){
        return dataId;
    }

    /**
    * 设置业务数据记录ID，根据DATA_SRC关联不同的数据表
    * @param dataId 业务数据记录ID，根据DATA_SRC关联不同的数据表
    */    
    public void setDataId(String dataId){
        this.dataId = dataId;
    }
    
    /**
    * 获取业务数据源类型 flowDoc-FLOW_DOC bizDoc-BIZ_DOC report
    */    
    public String getDataSrc(){
        return dataSrc;
    }

    /**
    * 设置业务数据源类型 flowDoc-FLOW_DOC bizDoc-BIZ_DOC report
    * @param dataSrc 业务数据源类型 flowDoc-FLOW_DOC bizDoc-BIZ_DOC report
    */    
    public void setDataSrc(String dataSrc){
        this.dataSrc = dataSrc;
    }
    
    /**
    * 获取打印任务创建方式 auto-自动创建 assign-指派 plan-依据计划产生
    */    
    public String getTaskSrc(){
        return taskSrc;
    }

    /**
    * 设置打印任务创建方式 auto-自动创建 assign-指派 plan-依据计划产生
    * @param taskSrc 打印任务创建方式 auto-自动创建 assign-指派 plan-依据计划产生
    */    
    public void setTaskSrc(String taskSrc){
        this.taskSrc = taskSrc;
    }
    
    /**
    * 获取打印模板ID, 关联 appsys.APP_PRINT_SETS.id
    */    
    public String getPrintTmplate(){
        return printTmplate;
    }

    /**
    * 设置打印模板ID, 关联 appsys.APP_PRINT_SETS.id
    * @param printTmplate 打印模板ID, 关联 appsys.APP_PRINT_SETS.id
    */    
    public void setPrintTmplate(String printTmplate){
        this.printTmplate = printTmplate;
    }
    
    /**
    * 获取打印任务的分组 -1 没有分组 其它值，对应需完成一组打印时的分组ID
    */    
    public String getGroupId(){
        return groupId;
    }

    /**
    * 设置打印任务的分组 -1 没有分组 其它值，对应需完成一组打印时的分组ID
    * @param groupId 打印任务的分组 -1 没有分组 其它值，对应需完成一组打印时的分组ID
    */    
    public void setGroupId(String groupId){
        this.groupId = groupId;
    }
    
    /**
    * 获取计划打印份数，根据队列设置，每个队列记录算一份
    */    
    public Double getPlanNum(){
        return planNum;
    }

    /**
    * 设置计划打印份数，根据队列设置，每个队列记录算一份
    * @param planNum 计划打印份数，根据队列设置，每个队列记录算一份
    */    
    public void setPlanNum(Double planNum){
        this.planNum = planNum;
    }
    
    /**
    * 获取已打印数量，当已打印数量与PLAN_NUM相同时，STATE=done
    */    
    public Double getPrintedCount(){
        return printedCount;
    }

    /**
    * 设置已打印数量，当已打印数量与PLAN_NUM相同时，STATE=done
    * @param printedCount 已打印数量，当已打印数量与PLAN_NUM相同时，STATE=done
    */    
    public void setPrintedCount(Double printedCount){
        this.printedCount = printedCount;
    }
    
    /**
    * 获取任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停
    */    
    public String getState(){
        return state;
    }

    /**
    * 设置任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停
    * @param state 任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停
    */    
    public void setState(String state){
        this.state = state;
    }
                                                    
    /**
    * 获取删除标志。-1:未删除
    */    
    public Integer getRecRemove(){
        return recRemove;
    }

    /**
    * 设置删除标志。-1:未删除
    * @param recRemove 删除标志。-1:未删除
    */    
    public void setRecRemove(Integer recRemove){
        this.recRemove = recRemove;
    }

    public List<AppdataPrintQueue> getPrintQueueList() {
        return printQueueList;
    }

    public void setPrintQueueList(List<AppdataPrintQueue> printQueueList) {
        this.printQueueList = printQueueList;
    }
}
