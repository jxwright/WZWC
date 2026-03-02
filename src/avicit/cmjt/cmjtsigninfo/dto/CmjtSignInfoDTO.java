package avicit.cmjt.cmjtsigninfo.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Id;
import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:44
* @类说明：合同签约信息表DTO
* @修改记录：
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtSignInfoDTO", description = "合同签约信息表")
@PojoRemark(table="CMJT_SIGN_INFO", object="CmjtSignInfoDTO", name="合同签约信息表")
public class CmjtSignInfoDTO extends BeanDTO{
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
    * 合同ID
    */
    @Size(max = 64, message = "合同ID长度不能超过64个字符")
    @ApiModelProperty(value = "合同ID", name = "contractId")
    @FieldRemark(column="CONTRACT_ID", field="contractId", name="合同ID")
    private String contractId;

    /**
    * 签约方ID
    */
    @Size(max = 64, message = "签约方ID长度不能超过64个字符")
    @ApiModelProperty(value = "签约方ID", name = "signId")
    @FieldRemark(column="SIGN_ID", field="signId", name="签约方ID")
    private String signId;

    /**
    * 签约方
    */
    @Size(max = 200, message = "签约方长度不能超过200个字符")
    @ApiModelProperty(value = "签约方", name = "signName")
    @FieldRemark(column="SIGN_NAME", field="signName", name="签约方")
    private String signName;

    /**
    * 合同身份
    */
    @Size(max = 20, message = "合同身份长度不能超过20个字符")
    @ApiModelProperty(value = "合同身份", name = "signParties")
    @FieldRemark(column="SIGN_PARTIES", field="signParties", name="合同身份")
    private String signParties;

    /**
    * 合同身份中文
    */
    @Size(max = 50, message = "合同身份中文长度不能超过50个字符")
    @ApiModelProperty(value = "合同身份中文", name = "signPartiesZhmc")
    @FieldRemark(column="SIGN_PARTIES_ZHMC", field="signPartiesZhmc", name="合同身份中文")
    private String signPartiesZhmc;

    /**
    * 签约方合同号
    */
    @Size(max = 100, message = "签约方合同号长度不能超过100个字符")
    @ApiModelProperty(value = "签约方合同号", name = "signContractNo")
    @FieldRemark(column="SIGN_CONTRACT_NO", field="signContractNo", name="签约方合同号")
    private String signContractNo;

    /**
    * 签约人
    */
    @Size(max = 100, message = "签约人长度不能超过100个字符")
    @ApiModelProperty(value = "签约人", name = "signContacts")
    @FieldRemark(column="SIGN_CONTACTS", field="signContacts", name="签约人")
    private String signContacts;

    /**
    * 签约方电话
    */
    @Size(max = 50, message = "签约方电话长度不能超过50个字符")
    @ApiModelProperty(value = "签约方电话", name = "signTel")
    @FieldRemark(column="SIGN_TEL", field="signTel", name="签约方电话")
    private String signTel;

    /**
    * 序号
    */
    @Size(max = 20, message = "序号长度不能超过20个字符")
    @ApiModelProperty(value = "序号", name = "orderNum")
    @FieldRemark(column="ORDER_NUM", field="orderNum", name="序号")
    private String orderNum;

    /**
    * 序号中文
    */
    @Size(max = 50, message = "序号中文长度不能超过50个字符")
    @ApiModelProperty(value = "序号中文", name = "orderNumZhmc")
    @FieldRemark(column="ORDER_NUM_ZHMC", field="orderNumZhmc", name="序号中文")
    private String orderNumZhmc;

    /**
    * 是否关联方
    */
    @Size(max = 2, message = "是否关联方长度不能超过2个字符")
    @ApiModelProperty(value = "是否关联方", name = "ynTransaction")
    @FieldRemark(column="YN_TRANSACTION", field="ynTransaction", name="是否关联方")
    private String ynTransaction;

    /**
    * 是否关联方中文
    */
    @Size(max = 10, message = "是否关联方中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否关联方中文", name = "ynTransactionZhmc")
    @FieldRemark(column="YN_TRANSACTION_ZHMC", field="ynTransactionZhmc", name="是否关联方中文")
    private String ynTransactionZhmc;

    /**
    * 关联方是否可编辑
    */
    @Size(max = 2, message = "关联方是否可编辑长度不能超过2个字符")
    @ApiModelProperty(value = "关联方是否可编辑", name = "editFlag")
    @FieldRemark(column="EDIT_FLAG", field="editFlag", name="关联方是否可编辑")
    private String editFlag;

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

    /**
     * 序号中文
     */
    @Size(max = 50, message = "社会信用代码长度不能超过50个字符")
    @ApiModelProperty(value = "社会信用代码", name = "unifiedSocialCreditCode")
    @FieldRemark(column="UNIFIED_SOCIAL_CREDIT_CODE", field="unifiedSocialCreditCode", name="社会信用代码")
    private String unifiedSocialCreditCode;
    /**
     * 序号中文
     */
    @Size(max = 50, message = "企业性质长度不能超过50个字符")
    @ApiModelProperty(value = "企业性质", name = "unitAttribute")
    @FieldRemark(column="UNIFIED_SOCIAL_CREDIT_CODE", field="unitAttribute", name="企业性质")
    private String unitAttribute;
    /**
     * 序号中文
     */
    @Size(max = 50, message = "企业性质长度不能超过50个字符")
    @ApiModelProperty(value = "企业性质", name = "supplierName")
    @FieldRemark(column="SUPPLIER_NAME", field="supplierName", name="企业性质")
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    public String getUnitAttribute() {
        return unitAttribute;
    }

    public void setUnitAttribute(String unitAttribute) {
        this.unitAttribute = unitAttribute;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getContractId(){
        return contractId;
    }

    public void setContractId(String contractId){
        this.contractId = contractId;
    }

    public String getSignId(){
        return signId;
    }

    public void setSignId(String signId){
        this.signId = signId;
    }

    public String getSignName(){
        return signName;
    }

    public void setSignName(String signName){
        this.signName = signName;
    }

    public String getSignParties(){
        return signParties;
    }

    public void setSignParties(String signParties){
        this.signParties = signParties;
    }

    public String getSignPartiesZhmc(){
        return signPartiesZhmc;
    }

    public void setSignPartiesZhmc(String signPartiesZhmc){
        this.signPartiesZhmc = signPartiesZhmc;
    }

    public String getSignContractNo(){
        return signContractNo;
    }

    public void setSignContractNo(String signContractNo){
        this.signContractNo = signContractNo;
    }

    public String getSignContacts(){
        return signContacts;
    }

    public void setSignContacts(String signContacts){
        this.signContacts = signContacts;
    }

    public String getSignTel(){
        return signTel;
    }

    public void setSignTel(String signTel){
        this.signTel = signTel;
    }

    public String getOrderNum(){
        return orderNum;
    }

    public void setOrderNum(String orderNum){
        this.orderNum = orderNum;
    }

    public String getOrderNumZhmc(){
        return orderNumZhmc;
    }

    public void setOrderNumZhmc(String orderNumZhmc){
        this.orderNumZhmc = orderNumZhmc;
    }

    public String getYnTransaction(){
        return ynTransaction;
    }

    public void setYnTransaction(String ynTransaction){
        this.ynTransaction = ynTransaction;
    }

    public String getYnTransactionZhmc(){
        return ynTransactionZhmc;
    }

    public void setYnTransactionZhmc(String ynTransactionZhmc){
        this.ynTransactionZhmc = ynTransactionZhmc;
    }

    public String getEditFlag(){
        return editFlag;
    }

    public void setEditFlag(String editFlag){
        this.editFlag = editFlag;
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
            return "合同签约信息表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "合同签约信息表";
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
