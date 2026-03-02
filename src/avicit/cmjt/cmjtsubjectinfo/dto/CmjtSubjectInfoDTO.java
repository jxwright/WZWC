package avicit.cmjt.cmjtsubjectinfo.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:44
* @类说明：合同标的物信息表DTO
* @修改记录：
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtSubjectInfoDTO", description = "合同标的物信息表")
@PojoRemark(table="CMJT_SUBJECT_INFO", object="CmjtSubjectInfoDTO", name="合同标的物信息表")
public class CmjtSubjectInfoDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 主键ID
    */
    @Size(max = 64, message = "主键ID长度不能超过64个字符")
    @ApiModelProperty(value = "主键ID", name = "id")
    @FieldRemark(column="ID", field="id", name="主键ID")
    private String id;

    /**
    * 关联合同ID
    */
    @Size(max = 64, message = "关联合同ID长度不能超过64个字符")
    @ApiModelProperty(value = "关联合同ID", name = "zbid")
    @FieldRemark(column="ZBID", field="zbid", name="关联合同ID")
    private String zbid;

    /**
    * 标的名称
    */
    @Size(max = 200, message = "标的名称长度不能超过200个字符")
    @ApiModelProperty(value = "标的名称", name = "cr00312001")
    @FieldRemark(column="CR00312001", field="cr00312001", name="标的名称")
    private String cr00312001;

    /**
    * 规格型号
    */
    @Size(max = 200, message = "规格型号长度不能超过200个字符")
    @ApiModelProperty(value = "规格型号", name = "cr00313001")
    @FieldRemark(column="CR00313001", field="cr00313001", name="规格型号")
    private String cr00313001;

    /**
    * 计量单位
    */
    @Size(max = 50, message = "计量单位长度不能超过50个字符")
    @ApiModelProperty(value = "计量单位", name = "cr00314001")
    @FieldRemark(column="CR00314001", field="cr00314001", name="计量单位")
    private String cr00314001;
    /**
    * 标的数量
    */
    @Digits(integer = 20, fraction = 2, message = "标的数量格式不正确")
    @ApiModelProperty(value = "标的数量", name = "cr00315001")
    @FieldRemark(column="CR00315001", field="cr00315001", name="标的数量")
    private java.math.BigDecimal cr00315001;
    /**
    * 标的单价
    */
    @Digits(integer = 20, fraction = 2, message = "标的单价格式不正确")
    @ApiModelProperty(value = "标的单价", name = "cr00316001")
    @FieldRemark(column="CR00316001", field="cr00316001", name="标的单价")
    private java.math.BigDecimal cr00316001;
    /**
    * 标的总价
    */
    @Digits(integer = 20, fraction = 2, message = "标的总价格式不正确")
    @ApiModelProperty(value = "标的总价", name = "cr00317001")
    @FieldRemark(column="CR00317001", field="cr00317001", name="标的总价")
    private java.math.BigDecimal cr00317001;
    /**
    * 税率
    */
    @Digits(integer = 10, fraction = 2, message = "税率格式不正确")
    @ApiModelProperty(value = "税率", name = "cr00318001")
    @FieldRemark(column="CR00318001", field="cr00318001", name="税率")
    private java.math.BigDecimal cr00318001;

    /**
    * 合同约定交付时间
    */
    @Size(max = 20, message = "合同约定交付时间长度不能超过20个字符")
    @ApiModelProperty(value = "合同约定交付时间", name = "cr00319001")
    @FieldRemark(column="CR00319001", field="cr00319001", name="合同约定交付时间")
    private String cr00319001;

    /**
    * 创建时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date creationDateBegin;

    /**
    * 创建时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date creationDateEnd;

    /**
    * 最后修改时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date lastUpdateDateBegin;

    /**
    * 最后修改时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.util.Date lastUpdateDateEnd;

    /**
    * 应用ID
    */
    @Size(max = 64, message = "应用ID长度不能超过64个字符")
    @ApiModelProperty(value = "应用ID", name = "sysApplicationId")
    @FieldRemark(column="SYS_APPLICATION_ID", field="sysApplicationId", name="应用ID")
    private String sysApplicationId;

    /**
    * 日志应用ID
    */
    @Size(max = 64, message = "日志应用ID长度不能超过64个字符")
    @ApiModelProperty(value = "日志应用ID", name = "logAppId")
    @FieldRemark(column="LOG_APP_ID", field="logAppId", name="日志应用ID")
    private String logAppId;

    /**
    * 操作类型
    */
    @Size(max = 50, message = "操作类型长度不能超过50个字符")
    @ApiModelProperty(value = "操作类型", name = "operationType")
    @FieldRemark(column="OPERATION_TYPE", field="operationType", name="操作类型")
    private String operationType;

    /**
    * 密级
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private String secretLevel;

    private String secretLevelName;


    @Size(max = 20, message = "数据id长度不能超过20个字符")
    @ApiModelProperty(value = "数据id", name = "businessId")
    @FieldRemark(column="BUSINESS_ID", field="businessId", name="数据id")
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getZbid(){
        return zbid;
    }

    public void setZbid(String zbid){
        this.zbid = zbid;
    }

    public String getCr00312001(){
        return cr00312001;
    }

    public void setCr00312001(String cr00312001){
        this.cr00312001 = cr00312001;
    }

    public String getCr00313001(){
        return cr00313001;
    }

    public void setCr00313001(String cr00313001){
        this.cr00313001 = cr00313001;
    }

    public String getCr00314001(){
        return cr00314001;
    }

    public void setCr00314001(String cr00314001){
        this.cr00314001 = cr00314001;
    }

    public java.math.BigDecimal getCr00315001(){
        return cr00315001;
    }

    public void setCr00315001(java.math.BigDecimal cr00315001){
        this.cr00315001 = cr00315001;
    }

    public java.math.BigDecimal getCr00316001(){
        return cr00316001;
    }

    public void setCr00316001(java.math.BigDecimal cr00316001){
        this.cr00316001 = cr00316001;
    }

    public java.math.BigDecimal getCr00317001(){
        return cr00317001;
    }

    public void setCr00317001(java.math.BigDecimal cr00317001){
        this.cr00317001 = cr00317001;
    }

    public java.math.BigDecimal getCr00318001(){
        return cr00318001;
    }

    public void setCr00318001(java.math.BigDecimal cr00318001){
        this.cr00318001 = cr00318001;
    }

    public String getCr00319001(){
        return cr00319001;
    }

    public void setCr00319001(String cr00319001){
        this.cr00319001 = cr00319001;
    }

    public java.util.Date getCreationDateBegin(){
        return creationDateBegin;
    }

    public void setCreationDateBegin(java.util.Date creationDateBegin){
        this.creationDateBegin = creationDateBegin;
    }

    public java.util.Date getCreationDateEnd(){
        return creationDateEnd;
    }

    public void setCreationDateEnd(java.util.Date creationDateEnd){
        this.creationDateEnd = creationDateEnd;
    }

    public java.util.Date getLastUpdateDateBegin(){
        return lastUpdateDateBegin;
    }

    public void setLastUpdateDateBegin(java.util.Date lastUpdateDateBegin){
        this.lastUpdateDateBegin = lastUpdateDateBegin;
    }

    public java.util.Date getLastUpdateDateEnd(){
        return lastUpdateDateEnd;
    }

    public void setLastUpdateDateEnd(java.util.Date lastUpdateDateEnd){
        this.lastUpdateDateEnd = lastUpdateDateEnd;
    }

    public String getSysApplicationId(){
        return sysApplicationId;
    }

    public void setSysApplicationId(String sysApplicationId){
        this.sysApplicationId = sysApplicationId;
    }

    public String getLogAppId(){
        return logAppId;
    }

    public void setLogAppId(String logAppId){
        this.logAppId = logAppId;
    }

    public String getOperationType(){
        return operationType;
    }

    public void setOperationType(String operationType){
        this.operationType = operationType;
    }

    public String getSecretLevel(){
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel){
        this.secretLevel = secretLevel;
    }

    public String getSecretLevelName(){
        return secretLevelName;
    }

    public void setSecretLevelName(String secretLevelName){
        this.secretLevelName = secretLevelName;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "合同标的物信息表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "合同标的物信息表";
        } else {
            return super.logTitle;
        }
    }

    @Override
    public PlatformConstant.LogType getLogType() {
        if (super.logType == null) {
            return PlatformConstant.LogType.module_operate;
        } else {
            return super.logType;
        }
    }
}
