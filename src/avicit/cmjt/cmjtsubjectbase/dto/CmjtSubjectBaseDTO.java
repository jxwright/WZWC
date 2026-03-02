package avicit.cmjt.cmjtsubjectbase.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Id;
import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 09:57
* @类说明：标的物库表DTO
* @修改记录：
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtSubjectBaseDTO", description = "标的物库表")
@PojoRemark(table="CMJT_SUBJECT_BASE", object="CmjtSubjectBaseDTO", name="标的物库表")
public class CmjtSubjectBaseDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 主键ID
    */
    @Id
    @Size(max = 36, message = "主键ID长度不能超过36个字符")
    @ApiModelProperty(value = "主键ID", name = "id")
    @FieldRemark(column="ID", field="id", name="主键ID")
    private String id;

    /**
    * 数据id
    */
    @LogField
    @NotBlank(message = "数据id不能为空")
    @Size(max = 20, message = "数据id长度不能超过20个字符")
    @ApiModelProperty(value = "数据id", name = "businessId")
    @FieldRemark(column="BUSINESS_ID", field="businessId", name="数据id")
    private String businessId;

    /**
    * 应用ID
    */
    @LogField
    @NotBlank(message = "应用ID不能为空")
    @Size(max = 36, message = "应用ID长度不能超过36个字符")
    @ApiModelProperty(value = "应用ID", name = "sysApplicationId")
    @FieldRemark(column="SYS_APPLICATION_ID", field="sysApplicationId", name="应用ID")
    private String sysApplicationId;

    /**
    * 标的名称
    */
    @LogField
    @NotBlank(message = "标的名称不能为空")
    @Size(max = 100, message = "标的名称长度不能超过100个字符")
    @ApiModelProperty(value = "标的名称", name = "subjectName")
    @FieldRemark(column="SUBJECT_NAME", field="subjectName", name="标的名称")
    private String subjectName;
    /**
    * 标的数量
    */
    @LogField
    @NotNull(message = "标的数量不能为空")
    @NumberFormat(pattern = "0.0000")
    @Digits(integer = 20, fraction = 4, message = "标的数量格式不正确")
    @ApiModelProperty(value = "标的数量", name = "subjectNum")
    @FieldRemark(column="SUBJECT_NUM", field="subjectNum", name="标的数量")
    private java.math.BigDecimal subjectNum;
    /**
    * 标的单价
    */
    @LogField
    @NotNull(message = "标的单价不能为空")
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "标的单价格式不正确")
    @ApiModelProperty(value = "标的单价", name = "subjectPrice")
    @FieldRemark(column="SUBJECT_PRICE", field="subjectPrice", name="标的单价")
    private java.math.BigDecimal subjectPrice;
    /**
    * 标的总价
    */
    @LogField
    @NotNull(message = "标的总价不能为空")
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "标的总价格式不正确")
    @ApiModelProperty(value = "标的总价", name = "subjectTotalAmount")
    @FieldRemark(column="SUBJECT_TOTAL_AMOUNT", field="subjectTotalAmount", name="标的总价")
    private java.math.BigDecimal subjectTotalAmount;

    /**
    * 合同约定交付时间
    */
    @Size(max = 10, message = "合同约定交付时间长度不能超过10个字符")
    @ApiModelProperty(value = "合同约定交付时间", name = "deliverDate")
    @FieldRemark(column="DELIVER_DATE", field="deliverDate", name="合同约定交付时间")
    private String deliverDate;
    /**
    * 增减率
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "增减率格式不正确")
    @ApiModelProperty(value = "增减率", name = "increDecreRate")
    @FieldRemark(column="INCRE_DECRE_RATE", field="increDecreRate", name="增减率")
    private java.math.BigDecimal increDecreRate;

    /**
    * 计划类型
    */
    @Size(max = 20, message = "计划类型长度不能超过20个字符")
    @ApiModelProperty(value = "计划类型", name = "planType")
    @FieldRemark(column="PLAN_TYPE", field="planType", name="计划类型")
    private String planType;

    /**
    * 规格型号
    */
    @Size(max = 100, message = "规格型号长度不能超过100个字符")
    @ApiModelProperty(value = "规格型号", name = "specsType")
    @FieldRemark(column="SPECS_TYPE", field="specsType", name="规格型号")
    private String specsType;

    /**
    * 税率
    */
    @Size(max = 20, message = "税率长度不能超过20个字符")
    @ApiModelProperty(value = "税率", name = "taxRate")
    @FieldRemark(column="TAX_RATE", field="taxRate", name="税率")
    private String taxRate;

    /**
    * 计量单位
    */
    @Size(max = 100, message = "计量单位长度不能超过100个字符")
    @ApiModelProperty(value = "计量单位", name = "unit")
    @FieldRemark(column="UNIT", field="unit", name="计量单位")
    private String unit;

    /**
    * 组织编码
    */
    @Size(max = 100, message = "组织编码长度不能超过100个字符")
    @ApiModelProperty(value = "组织编码", name = "orgCode")
    @FieldRemark(column="ORG_CODE", field="orgCode", name="组织编码")
    private String orgCode;

    /**
    * 占用状态
    */
    @Size(max = 1, message = "占用状态长度不能超过1个字符")
    @ApiModelProperty(value = "占用状态", name = "isOccupy")
    @FieldRemark(column="IS_OCCUPY", field="isOccupy", name="占用状态")
    private String isOccupy;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date lastUpdateDateBegin;

    /**
    * 最后修改时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date lastUpdateDateEnd;

    /**
    * 密级
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private String secretLevel;

    private String secretLevelName;

    /**
    * 数据来源
    */
    @Size(max = 10, message = "数据来源长度不能超过10个字符")
    @ApiModelProperty(value = "数据来源", name = "dataSource")
    @FieldRemark(column="DATA_SOURCE", field="dataSource", name="数据来源")
    private String dataSource;

    /**
    * 是否推送
    */
    @Size(max = 10, message = "是否推送长度不能超过10个字符")
    @ApiModelProperty(value = "是否推送", name = "isTrans")
    @FieldRemark(column="IS_TRANS", field="isTrans", name="是否推送",dataType="lookup",lookupType = "ONLYORNOT")
    private String isTrans;

    /**
     * 是否推送
     */
    private String isTransName;

    /**
     * 操作类型
     */
    @Size(max = 10, message = "操作类型长度不能超过10个字符")
    @ApiModelProperty(value = "操作类型", name = "operationType")
    @FieldRemark(column="OPERATION_TYPE", field="operationType", name="操作类型")
    private String operationType;

    /**
     * 集团主键ID
     */
    @Size(max = 36, message = "集团主键ID长度不能超过36个字符")
    @ApiModelProperty(value = "集团主键ID", name = "jtId")
    @FieldRemark(column="JT_ID", field="jtId", name="集团主键ID")
    private String jtId;

    /**
     * 推送失败原因
     */
    @FieldRemark(column="ERROR_INFO", field="errorInfo", name="推送失败原因")
    private String errorInfo;

    @FieldRemark(column="TASK_NO", field="taskNo", name="")
    private String taskNo;
    @FieldRemark(column="TASK_TYPE", field="taskType", name="推送失败原因")
    private String taskType;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getBusinessId(){
        return businessId;
    }

    public void setBusinessId(String businessId){
        this.businessId = businessId;
    }

    public String getSysApplicationId(){
        return sysApplicationId;
    }

    public void setSysApplicationId(String sysApplicationId){
        this.sysApplicationId = sysApplicationId;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }

    public java.math.BigDecimal getSubjectNum(){
        return subjectNum;
    }

    public void setSubjectNum(java.math.BigDecimal subjectNum){
        this.subjectNum = subjectNum;
    }

    public java.math.BigDecimal getSubjectPrice(){
        return subjectPrice;
    }

    public void setSubjectPrice(java.math.BigDecimal subjectPrice){
        this.subjectPrice = subjectPrice;
    }

    public java.math.BigDecimal getSubjectTotalAmount(){
        return subjectTotalAmount;
    }

    public void setSubjectTotalAmount(java.math.BigDecimal subjectTotalAmount){
        this.subjectTotalAmount = subjectTotalAmount;
    }

    public String getDeliverDate(){
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate){
        this.deliverDate = deliverDate;
    }

    public java.math.BigDecimal getIncreDecreRate(){
        return increDecreRate;
    }

    public void setIncreDecreRate(java.math.BigDecimal increDecreRate){
        this.increDecreRate = increDecreRate;
    }

    public String getPlanType(){
        return planType;
    }

    public void setPlanType(String planType){
        this.planType = planType;
    }

    public String getSpecsType(){
        return specsType;
    }

    public void setSpecsType(String specsType){
        this.specsType = specsType;
    }

    public String getTaxRate(){
        return taxRate;
    }

    public void setTaxRate(String taxRate){
        this.taxRate = taxRate;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public String getOrgCode(){
        return orgCode;
    }

    public void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }

    public String getIsOccupy(){
        return isOccupy;
    }

    public void setIsOccupy(String isOccupy){
        this.isOccupy = isOccupy;
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

    public String getDataSource(){
        return dataSource;
    }

    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
    }

    public String getIsTrans(){
        return isTrans;
    }

    public void setIsTrans(String isTrans){
        this.isTrans = isTrans;
    }

    public String getIsTransName(){
        return isTransName;
    }

    public void setIsTransName(String isTransName){
        this.isTransName = isTransName;
    }

    public String getOperationType(){
        return operationType;
    }

    public void setOperationType(String operationType){
        this.operationType = operationType;
    }

    public String getJtId(){
        return jtId;
    }

    public void setJtId(String jtId){
        this.jtId = jtId;
    }

    public String getErrorInfo(){
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo){
        this.errorInfo = errorInfo;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "标的物库表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "标的物库表";
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
