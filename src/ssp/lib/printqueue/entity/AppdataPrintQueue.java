package ssp.lib.printqueue.entity;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import ssp.ddd.support.entity.Entity;

import javax.persistence.Id;


public class AppdataPrintQueue extends Entity<String> {
    
    /**
    * 表主键({GUID})
    */    
    @Id
    @LogField
    @FieldRemark(column="id",field="ID",name="表主键({GUID})")
    private String id;
    
    /**
    * 打印任务ID, 关联 appdata.APPDATA_PRINT_TASKS.id
    */    
    @LogField
    @FieldRemark(column="taskId",field="TASK_ID",name="打印任务ID, 关联 appdata.APPDATA_PRINT_TASKS.id")
    private String taskId;
        
    /**
    * 业务数据记录ID，根据appdata.APPDATA_PRINT_TASKS.DATA_SRC关联不同的数据表
    */    
    @LogField
    @FieldRemark(column="dataId",field="DATA_ID",name="业务数据记录ID，根据appdata.APPDATA_PRINT_TASKS.DATA_SRC关联不同的数据表")
    private String dataId;
        
    /**
    * 打印者ID, 关联 apps.SYS_USER.id
    */    
    @LogField
    @FieldRemark(column="printerId",field="PRINTER_ID",name="打印者ID, 关联 apps.SYS_USER.id")
    private String printerId;
        
    /**
    * 此打印任务此打印人在队列中是否有效，当 STATE-done,abort,cancel,pause时，为无效状态
    */    
    @LogField
    @FieldRemark(column="isValid",field="IS_VALID",name="此打印任务此打印人在队列中是否有效，当 STATE-done,abort,cancel,pause时，为无效状态")
    private String isValid;
        
    /**
    * 任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停
    */    
    @LogField
    @FieldRemark(column="state",field="STATE",name="任务完成状态 waiting-等待执行 doing-执行中 done-完成 abort-中止 cancel-取消 pause-暂停")
    private String state;
        
    /**
    * 已打印次数，默认 0, 每执行一次打印，此计数增加 1
    */    
    @LogField
    @FieldRemark(column="printedCount",field="PRINTED_COUNT",name="已打印次数，默认 0, 每执行一次打印，此计数增加 1")
    private Double printedCount;
    
    /**
    * 获取表主键({GUID})
    */    
    public String getId(){
        return id;
    }

    /**
    * 设置表主键({GUID})
    * @param id 表主键({GUID})
    */    
    public void setId(String id){
        this.id = id;
    }
    
    /**
    * 获取打印任务ID, 关联 appdata.APPDATA_PRINT_TASKS.id
    */    
    public String getTaskId(){
        return taskId;
    }

    /**
    * 设置打印任务ID, 关联 appdata.APPDATA_PRINT_TASKS.id
    * @param taskId 打印任务ID, 关联 appdata.APPDATA_PRINT_TASKS.id
    */    
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }
    
    /**
    * 获取业务数据记录ID，根据appdata.APPDATA_PRINT_TASKS.DATA_SRC关联不同的数据表
    */    
    public String getDataId(){
        return dataId;
    }

    /**
    * 设置业务数据记录ID，根据appdata.APPDATA_PRINT_TASKS.DATA_SRC关联不同的数据表
    * @param dataId 业务数据记录ID，根据appdata.APPDATA_PRINT_TASKS.DATA_SRC关联不同的数据表
    */    
    public void setDataId(String dataId){
        this.dataId = dataId;
    }
    
    /**
    * 获取打印者ID, 关联 apps.SYS_USER.id
    */    
    public String getPrinterId(){
        return printerId;
    }

    /**
    * 设置打印者ID, 关联 apps.SYS_USER.id
    * @param printerId 打印者ID, 关联 apps.SYS_USER.id
    */    
    public void setPrinterId(String printerId){
        this.printerId = printerId;
    }
    
    /**
    * 获取此打印任务此打印人在队列中是否有效，当 STATE-done,abort,cancel,pause时，为无效状态
    */    
    public String getIsValid(){
        return isValid;
    }

    /**
    * 设置此打印任务此打印人在队列中是否有效，当 STATE-done,abort,cancel,pause时，为无效状态
    * @param isValid 此打印任务此打印人在队列中是否有效，当 STATE-done,abort,cancel,pause时，为无效状态
    */    
    public void setIsValid(String isValid){
        this.isValid = isValid;
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
    * 获取已打印次数，默认 0, 每执行一次打印，此计数增加 1
    */    
    public Double getPrintedCount(){
        return printedCount;
    }

    /**
    * 设置已打印次数，默认 0, 每执行一次打印，此计数增加 1
    * @param printedCount 已打印次数，默认 0, 每执行一次打印，此计数增加 1
    */    
    public void setPrintedCount(Double printedCount){
        this.printedCount = printedCount;
    }
}
