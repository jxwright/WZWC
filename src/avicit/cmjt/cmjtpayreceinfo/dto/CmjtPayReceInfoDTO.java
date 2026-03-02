package avicit.cmjt.cmjtpayreceinfo.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Id;
import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:47
* @类说明：合同收付款计划信息表DTO
* @修改记录：
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtPayReceInfoDTO", description = "合同收付款计划信息表")
@PojoRemark(table="CMJT_PAY_RECE_INFO", object="CmjtPayReceInfoDTO", name="合同收付款计划信息表")
public class CmjtPayReceInfoDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 主键ID
    */
    @Id
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
    * 款项性质
    */
    @Size(max = 200, message = "款项性质长度不能超过200个字符")
    @ApiModelProperty(value = "款项性质", name = "cr00321001")
    @FieldRemark(column="CR00321001", field="cr00321001", name="款项性质")
    private String cr00321001;

    /**
    * 结算条件
    */
    @Size(max = 200, message = "结算条件长度不能超过200个字符")
    @ApiModelProperty(value = "结算条件", name = "cr00322001")
    @FieldRemark(column="CR00322001", field="cr00322001", name="结算条件")
    private String cr00322001;

    /**
    * 计划付款时间
    */
    @Size(max = 20, message = "计划付款时间长度不能超过20个字符")
    @ApiModelProperty(value = "计划付款时间", name = "cr00323001")
    @FieldRemark(column="CR00323001", field="cr00323001", name="计划付款时间")
    private String cr00323001;
    /**
    * 金额
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "金额格式不正确")
    @ApiModelProperty(value = "金额", name = "cr00324001")
    @FieldRemark(column="CR00324001", field="cr00324001", name="金额")
    private java.math.BigDecimal cr00324001;
    /**
    * 税率
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 10, fraction = 2, message = "税率格式不正确")
    @ApiModelProperty(value = "税率", name = "cr00325001")
    @FieldRemark(column="CR00325001", field="cr00325001", name="税率")
    private java.math.BigDecimal cr00325001;
    /**
    * 税额
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "税额格式不正确")
    @ApiModelProperty(value = "税额", name = "cr00326001")
    @FieldRemark(column="CR00326001", field="cr00326001", name="税额")
    private java.math.BigDecimal cr00326001;

    /**
    * 币种
    */
    @Size(max = 20, message = "币种长度不能超过20个字符")
    @ApiModelProperty(value = "币种", name = "cr00327001")
    @FieldRemark(column="CR00327001", field="cr00327001", name="币种")
    private String cr00327001;

    /**
    * 币种中文
    */
    @Size(max = 50, message = "币种中文长度不能超过50个字符")
    @ApiModelProperty(value = "币种中文", name = "cr00327001Zhmc")
    @FieldRemark(column="CR00327001_ZHMC", field="cr00327001Zhmc", name="币种中文")
    private String cr00327001Zhmc;
    /**
    * 汇率
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 10, fraction = 2, message = "汇率格式不正确")
    @ApiModelProperty(value = "汇率", name = "cr00328001")
    @FieldRemark(column="CR00328001", field="cr00328001", name="汇率")
    private java.math.BigDecimal cr00328001;
    /**
    * 金额（人民币）
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "金额（人民币）格式不正确")
    @ApiModelProperty(value = "金额（人民币）", name = "cr00329001")
    @FieldRemark(column="CR00329001", field="cr00329001", name="金额（人民币）")
    private java.math.BigDecimal cr00329001;

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
    * 操作类型
    */
    @Size(max = 50, message = "操作类型长度不能超过50个字符")
    @ApiModelProperty(value = "操作类型", name = "operationType")
    @FieldRemark(column="OPERATION_TYPE_", field="operationType", name="操作类型")
    private String operationType;

    /**
    * 密级
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private String secretLevel;

    private String secretLevelName;

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

    public String getCr00321001(){
        return cr00321001;
    }

    public void setCr00321001(String cr00321001){
        this.cr00321001 = cr00321001;
    }

    public String getCr00322001(){
        return cr00322001;
    }

    public void setCr00322001(String cr00322001){
        this.cr00322001 = cr00322001;
    }

    public String getCr00323001(){
        return cr00323001;
    }

    public void setCr00323001(String cr00323001){
        this.cr00323001 = cr00323001;
    }

    public java.math.BigDecimal getCr00324001(){
        return cr00324001;
    }

    public void setCr00324001(java.math.BigDecimal cr00324001){
        this.cr00324001 = cr00324001;
    }

    public java.math.BigDecimal getCr00325001(){
        return cr00325001;
    }

    public void setCr00325001(java.math.BigDecimal cr00325001){
        this.cr00325001 = cr00325001;
    }

    public java.math.BigDecimal getCr00326001(){
        return cr00326001;
    }

    public void setCr00326001(java.math.BigDecimal cr00326001){
        this.cr00326001 = cr00326001;
    }

    public String getCr00327001(){
        return cr00327001;
    }

    public void setCr00327001(String cr00327001){
        this.cr00327001 = cr00327001;
    }

    public String getCr00327001Zhmc(){
        return cr00327001Zhmc;
    }

    public void setCr00327001Zhmc(String cr00327001Zhmc){
        this.cr00327001Zhmc = cr00327001Zhmc;
    }

    public java.math.BigDecimal getCr00328001(){
        return cr00328001;
    }

    public void setCr00328001(java.math.BigDecimal cr00328001){
        this.cr00328001 = cr00328001;
    }

    public java.math.BigDecimal getCr00329001(){
        return cr00329001;
    }

    public void setCr00329001(java.math.BigDecimal cr00329001){
        this.cr00329001 = cr00329001;
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
            return "合同收付款计划信息表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "合同收付款计划信息表";
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
