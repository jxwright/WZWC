package ssp.lib.printqueue.entity;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import ssp.ddd.support.entity.Entity;

import javax.persistence.Id;


public class AppPrintSets extends Entity<String> {

    private static final long serialVersionUID = 230310000779766810L;
    
    /**
    * 表主键({GUID})
    */    
    @Id
    @LogField
    @FieldRemark(column="id",field="ID",name="表主键({GUID})")
    private String id;
    
    /**
    * 模板名称
    */    
    @LogField
    @FieldRemark(column="templateName",field="TEMPLATE_NAME",name="模板名称")
    private String templateName;
        
    /**
    * 打印类型 doc-单据 report-报表
    */    
    @LogField
    @FieldRemark(column="printType",field="PRINT_TYPE",name="打印类型 doc-单据 report-报表")
    private String printType;
        
    /**
    * 打印方式 RQ-润乾
    */    
    @LogField
    @FieldRemark(column="printChannel",field="PRINT_CHANNEL",name="打印方式 RQ-润乾")
    private String printChannel;
        
    /**
    * 打印模板文件
    */    
    @LogField
    @FieldRemark(column="templateFile",field="TEMPLATE_FILE",name="打印模板文件")
    private String templateFile;
        
    /**
    * 打印设置备注说明
    */    
    @LogField
    @FieldRemark(column="description",field="DESCRIPTION",name="打印设置备注说明")
    private String description;
        
    /**
    * 选项(json)
    */    
    @LogField
    @FieldRemark(column="optionData",field="OPTION_DATA",name="选项(json)")
    private String optionData;
        
    /**
    * 模板版本
    */    
    @LogField
    @FieldRemark(column="templateVer",field="TEMPLATE_VER",name="模板版本")
    private String templateVer;
        
    /**
    * 模板标题
    */    
    @LogField
    @FieldRemark(column="templateTitle",field="TEMPLATE_TITLE",name="模板标题")
    private String templateTitle;
    
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
    * 获取模板名称
    */    
    public String getTemplateName(){
        return templateName;
    }

    /**
    * 设置模板名称
    * @param templateName 模板名称
    */    
    public void setTemplateName(String templateName){
        this.templateName = templateName;
    }
    
    /**
    * 获取打印类型 doc-单据 report-报表
    */    
    public String getPrintType(){
        return printType;
    }

    /**
    * 设置打印类型 doc-单据 report-报表
    * @param printType 打印类型 doc-单据 report-报表
    */    
    public void setPrintType(String printType){
        this.printType = printType;
    }
    
    /**
    * 获取打印方式 RQ-润乾
    */    
    public String getPrintChannel(){
        return printChannel;
    }

    /**
    * 设置打印方式 RQ-润乾
    * @param printChannel 打印方式 RQ-润乾
    */    
    public void setPrintChannel(String printChannel){
        this.printChannel = printChannel;
    }
    
    /**
    * 获取打印模板文件
    */    
    public String getTemplateFile(){
        return templateFile;
    }

    /**
    * 设置打印模板文件
    * @param templateFile 打印模板文件
    */    
    public void setTemplateFile(String templateFile){
        this.templateFile = templateFile;
    }
    
    /**
    * 获取打印设置备注说明
    */    
    public String getDescription(){
        return description;
    }

    /**
    * 设置打印设置备注说明
    * @param description 打印设置备注说明
    */    
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
    * 获取选项(json)
    */    
    public String getOptionData(){
        return optionData;
    }

    /**
    * 设置选项(json)
    * @param optionData 选项(json)
    */    
    public void setOptionData(String optionData){
        this.optionData = optionData;
    }
    
    /**
    * 获取模板版本
    */    
    public String getTemplateVer(){
        return templateVer;
    }

    /**
    * 设置模板版本
    * @param templateVer 模板版本
    */    
    public void setTemplateVer(String templateVer){
        this.templateVer = templateVer;
    }
    
    /**
    * 获取模板标题
    */    
    public String getTemplateTitle(){
        return templateTitle;
    }

    /**
    * 设置模板标题
    * @param templateTitle 模板标题
    */    
    public void setTemplateTitle(String templateTitle){
        this.templateTitle = templateTitle;
    }
}
