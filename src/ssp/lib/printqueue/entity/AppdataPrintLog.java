package ssp.lib.printqueue.entity;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import ssp.ddd.support.entity.Entity;

import javax.persistence.Id;
import java.util.Date;

public class AppdataPrintLog extends Entity<String> {
    
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
    * 打印队列ID, 关联 appdata.APPDATA_PRINT_QUEUE.id
    */    
    @LogField
    @FieldRemark(column="queueId",field="QUEUE_ID",name="打印队列ID, 关联 appdata.APPDATA_PRINT_QUEUE.id")
    private String queueId;
        
    /**
    * 打印者ID, 关联 apps.SYS_USER.id
    */    
    @LogField
    @FieldRemark(column="printerId",field="PRINTER_ID",name="打印者ID, 关联 apps.SYS_USER.id")
    private String printerId;
        
    /**
    * 打印者(+工号)
    */    
    @LogField
    @FieldRemark(column="printer",field="PRINTER",name="打印者(+工号)")
    private String printer;
        
    /**
    * 第几次打印，一般根据appdata.APPDATA_PRINT_QUEUE.printed_count+1得到此值
    */    
    @LogField
    @FieldRemark(column="printNum",field="PRINT_NUM",name="第几次打印，一般根据appdata.APPDATA_PRINT_QUEUE.printed_count+1得到此值")
    private Double printNum;
        
    /**
    * 打印时间
    */    
    @LogField
    @FieldRemark(column="printDate",field="PRINT_DATE",name="打印时间")
    private Date printDate;
        
    /**
    * 是否打印成功
    */    
    @LogField
    @FieldRemark(column="isSuccess",field="IS_SUCCESS",name="是否打印成功")
    private String isSuccess;
        
    /**
    * 打印情况说明
    */    
    @LogField
    @FieldRemark(column="description",field="DESCRIPTION",name="打印情况说明")
    private String description;
                                                        
    /**
    * 删除标志。-1:未删除
    */    
    @LogField
    @FieldRemark(column="recRemove",field="REC_REMOVE",name="删除标志。-1:未删除")
    private Integer recRemove;
    
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
    * 获取打印队列ID, 关联 appdata.APPDATA_PRINT_QUEUE.id
    */    
    public String getQueueId(){
        return queueId;
    }

    /**
    * 设置打印队列ID, 关联 appdata.APPDATA_PRINT_QUEUE.id
    * @param queueId 打印队列ID, 关联 appdata.APPDATA_PRINT_QUEUE.id
    */    
    public void setQueueId(String queueId){
        this.queueId = queueId;
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
    * 获取打印者(+工号)
    */    
    public String getPrinter(){
        return printer;
    }

    /**
    * 设置打印者(+工号)
    * @param printer 打印者(+工号)
    */    
    public void setPrinter(String printer){
        this.printer = printer;
    }
    
    /**
    * 获取第几次打印，一般根据appdata.APPDATA_PRINT_QUEUE.printed_count+1得到此值
    */    
    public Double getPrintNum(){
        return printNum;
    }

    /**
    * 设置第几次打印，一般根据appdata.APPDATA_PRINT_QUEUE.printed_count+1得到此值
    * @param printNum 第几次打印，一般根据appdata.APPDATA_PRINT_QUEUE.printed_count+1得到此值
    */    
    public void setPrintNum(Double printNum){
        this.printNum = printNum;
    }
    
    /**
    * 获取打印时间
    */    
    public Date getPrintDate(){
        return printDate;
    }

    /**
    * 设置打印时间
    * @param printDate 打印时间
    */    
    public void setPrintDate(Date printDate){
        this.printDate = printDate;
    }
    
    /**
    * 获取是否打印成功
    */    
    public String getIsSuccess(){
        return isSuccess;
    }

    /**
    * 设置是否打印成功
    * @param isSuccess 是否打印成功
    */    
    public void setIsSuccess(String isSuccess){
        this.isSuccess = isSuccess;
    }
    
    /**
    * 获取打印情况说明
    */    
    public String getDescription(){
        return description;
    }

    /**
    * 设置打印情况说明
    * @param description 打印情况说明
    */    
    public void setDescription(String description){
        this.description = description;
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
}
