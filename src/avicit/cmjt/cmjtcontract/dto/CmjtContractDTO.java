package avicit.cmjt.cmjtcontract.dto;

import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
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
import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 11:31
* @类说明：合同主表DTO
* @修改记录：
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtContractDTO", description = "合同主表")
@PojoRemark(table="CMJT_CONTRACT", object="CmjtContractDTO", name="合同主表")
public class CmjtContractDTO extends BeanDTO{
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
    * 合同编号
    */
    @Size(max = 100, message = "合同编号长度不能超过100个字符")
    @ApiModelProperty(value = "合同编号", name = "contractNo")
    @FieldRemark(column="CONTRACT_NO", field="contractNo", name="合同编号")
    private String contractNo;

    /**
    * 合同名称
    */
    @Size(max = 200, message = "合同名称长度不能超过200个字符")
    @ApiModelProperty(value = "合同名称", name = "contractName")
    @FieldRemark(column="CONTRACT_NAME", field="contractName", name="合同名称")
    private String contractName;

    /**
    * 合同状态
    */
    @Size(max = 20, message = "合同状态长度不能超过20个字符")
    @ApiModelProperty(value = "合同状态", name = "contractState")
    @FieldRemark(column="CONTRACT_STATE", field="contractState", name="合同状态")
    private String contractState;
    /**
    * 人民币金额小写
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "人民币金额小写格式不正确")
    @ApiModelProperty(value = "人民币金额小写", name = "contractMoney")
    @FieldRemark(column="CONTRACT_MONEY", field="contractMoney", name="人民币金额小写")
    private java.math.BigDecimal contractMoney;

    /**
    * 人民币金额大写
    */
    @Size(max = 200, message = "人民币金额大写长度不能超过200个字符")
    @ApiModelProperty(value = "人民币金额大写", name = "contractMoneyUpper")
    @FieldRemark(column="CONTRACT_MONEY_UPPER", field="contractMoneyUpper", name="人民币金额大写")
    private String contractMoneyUpper;
    /**
    * 合同总金额
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "合同总金额格式不正确")
    @ApiModelProperty(value = "合同总金额", name = "contractWbMoney")
    @FieldRemark(column="CONTRACT_WB_MONEY", field="contractWbMoney", name="合同总金额")
    private java.math.BigDecimal contractWbMoney;

    /**
    * 合同类型
    */
    @Size(max = 100, message = "合同类型长度不能超过100个字符")
    @ApiModelProperty(value = "合同类型", name = "contractClassifyName")
    @FieldRemark(column="CONTRACT_CLASSIFY_NAME", field="contractClassifyName", name="合同类型")
    private String contractClassifyName;

    /**
    * 合同二级分类
    */
    @Size(max = 100, message = "合同二级分类长度不能超过100个字符")
    @ApiModelProperty(value = "合同二级分类", name = "contractClasstypeName")
    @FieldRemark(column="CONTRACT_CLASSTYPE_NAME", field="contractClasstypeName", name="合同二级分类")
    private String contractClasstypeName;

    /**
    * 合同开始日期
    */
    @Size(max = 20, message = "合同开始日期长度不能超过20个字符")
    @ApiModelProperty(value = "合同开始日期", name = "contractEstimateBegin")
    @FieldRemark(column="CONTRACT_ESTIMATE_BEGIN", field="contractEstimateBegin", name="合同开始日期")
    private String contractEstimateBegin;

    /**
    * 合同结束日期
    */
    @Size(max = 20, message = "合同结束日期长度不能超过20个字符")
    @ApiModelProperty(value = "合同结束日期", name = "contractEstimateEnd")
    @FieldRemark(column="CONTRACT_ESTIMATE_END", field="contractEstimateEnd", name="合同结束日期")
    private String contractEstimateEnd;

    /**
    * 合同身份
    */
    @Size(max = 20, message = "合同身份长度不能超过20个字符")
    @ApiModelProperty(value = "合同身份", name = "contractingParties")
    @FieldRemark(column="CONTRACTING_PARTIES", field="contractingParties", name="合同身份")
    private String contractingParties;

    /**
    * 令号
    */
    @Size(max = 50, message = "令号长度不能超过50个字符")
    @ApiModelProperty(value = "令号", name = "cmdOrder")
    @FieldRemark(column="CMD_ORDER", field="cmdOrder", name="令号")
    private String cmdOrder;

    /**
    * 业务领域
    */
    @Size(max = 50, message = "业务领域长度不能超过50个字符")
    @ApiModelProperty(value = "业务领域", name = "businessArea")
    @FieldRemark(column="BUSINESS_AREA", field="businessArea", name="业务领域")
    private String businessArea;

    /**
     * 合同一级分
     **/
    @Size(max = 100, message = "合同一级分类长度不能超过100个字符")
    @ApiModelProperty(value = "合同一级分类", name = "attribute02")
    @FieldRemark(column="ATTRIBUTE02", field="attribute02", name="合同一级分类")
    private String attribute02;

    /**
    * 军品合同类型
    */
    @Size(max = 50, message = "军品合同类型长度不能超过50个字符")
    @ApiModelProperty(value = "军品合同类型", name = "militaryCmType")
    @FieldRemark(column="MILITARY_CM_TYPE", field="militaryCmType", name="军品合同类型")
    private String militaryCmType;

    /**
    * 科研合同类型
    */
    @Size(max = 50, message = "科研合同类型长度不能超过50个字符")
    @ApiModelProperty(value = "科研合同类型", name = "scientificCmType")
    @FieldRemark(column="SCIENTIFIC_CM_TYPE", field="scientificCmType", name="科研合同类型")
    private String scientificCmType;

    /**
    * 项目密级
    */
    @Size(max = 50, message = "项目密级长度不能超过50个字符")
    @ApiModelProperty(value = "项目密级", name = "itemSecret")
    @FieldRemark(column="ITEM_SECRET", field="itemSecret", name="项目密级")
    private String itemSecret;

    /**
    * 项目代号
    */
    @Size(max = 100, message = "项目代号长度不能超过100个字符")
    @ApiModelProperty(value = "项目代号", name = "projectCode")
    @FieldRemark(column="PROJECT_CODE", field="projectCode", name="项目代号")
    private String projectCode;

    /**
    * 项目简称
    */
    @Size(max = 100, message = "项目简称长度不能超过100个字符")
    @ApiModelProperty(value = "项目简称", name = "projectAbbreviation")
    @FieldRemark(column="PROJECT_ABBREVIATION", field="projectAbbreviation", name="项目简称")
    private String projectAbbreviation;

    /**
    * 项目类型
    */
    @Size(max = 100, message = "项目类型长度不能超过100个字符")
    @ApiModelProperty(value = "项目类型", name = "projectType")
    @FieldRemark(column="PROJECT_TYPE", field="projectType", name="项目类型")
    private String projectType;

    /**
    * 币种
    */
    @Size(max = 20, message = "币种长度不能超过20个字符")
    @ApiModelProperty(value = "币种", name = "currency")
    @FieldRemark(column="CURRENCY", field="currency", name="币种")
    private String currency;

    /**
    * 币种中文名称
    */
    @Size(max = 50, message = "币种中文名称长度不能超过50个字符")
    @ApiModelProperty(value = "币种中文名称", name = "currencyZhmc")
    @FieldRemark(column="CURRENCY_ZHMC", field="currencyZhmc", name="币种中文名称")
    private String currencyZhmc;
    /**
    * 汇率
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 10, fraction = 2, message = "汇率格式不正确")
    @ApiModelProperty(value = "汇率", name = "exchangeRate")
    @FieldRemark(column="EXCHANGE_RATE", field="exchangeRate", name="汇率")
    private java.math.BigDecimal exchangeRate;

    /**
    * 是否外贸代理公司
    */
    @Size(max = 2, message = "是否外贸代理公司长度不能超过2个字符")
    @ApiModelProperty(value = "是否外贸代理公司", name = "multiContract")
    @FieldRemark(column="MULTI_CONTRACT", field="multiContract", name="是否外贸代理公司")
    private String multiContract;

    /**
    * 我方签约公司id
    */
    @Size(max = 64, message = "我方签约公司id长度不能超过64个字符")
    @ApiModelProperty(value = "我方签约公司id", name = "signOrg")
    @FieldRemark(column="SIGN_ORG", field="signOrg", name="我方签约公司id")
    private String signOrg;

    /**
    * 单位全称
    */
    @Size(max = 200, message = "单位全称长度不能超过200个字符")
    @ApiModelProperty(value = "单位全称", name = "signOrgName")
    @FieldRemark(column="SIGN_ORG_NAME", field="signOrgName", name="单位全称")
    private String signOrgName;

    /**
    * 统一社会信用代码
    */
    @Size(max = 50, message = "统一社会信用代码长度不能超过50个字符")
    @ApiModelProperty(value = "统一社会信用代码", name = "unifiedSocialCreditCode")
    @FieldRemark(column="UNIFIED_SOCIAL_CREDIT_CODE", field="unifiedSocialCreditCode", name="统一社会信用代码")
    private String unifiedSocialCreditCode;

    /**
    * 制造商/生产商
    */
    @Size(max = 200, message = "制造商/生产商长度不能超过200个字符")
    @ApiModelProperty(value = "制造商/生产商", name = "supplierName")
    @FieldRemark(column="SUPPLIER_NAME", field="supplierName", name="制造商/生产商")
    private String supplierName;

    /**
    * 对方合同编号
    */
    @Size(max = 100, message = "对方合同编号长度不能超过100个字符")
    @ApiModelProperty(value = "对方合同编号", name = "supplierContactNo")
    @FieldRemark(column="SUPPLIER_CONTACT_NO", field="supplierContactNo", name="对方合同编号")
    private String supplierContactNo;

    /**
    * 采购方式
    */
    @Size(max = 50, message = "采购方式长度不能超过50个字符")
    @ApiModelProperty(value = "采购方式", name = "purchaseMode")
    @FieldRemark(column="PURCHASE_MODE", field="purchaseMode", name="采购方式")
    private String purchaseMode;

    /**
    * 用印类型id
    */
    @Size(max = 50, message = "用印类型id长度不能超过50个字符")
    @ApiModelProperty(value = "用印类型id", name = "sealType")
    @FieldRemark(column="SEAL_TYPE", field="sealType", name="用印类型id")
    private String sealType;

    /**
    * 用印类型
    */
    @Size(max = 200, message = "用印类型长度不能超过200个字符")
    @ApiModelProperty(value = "用印类型", name = "sealTypeName")
    @FieldRemark(column="SEAL_TYPE_NAME", field="sealTypeName", name="用印类型")
    private String sealTypeName;

    /**
    * 是否用印
    */
    @Size(max = 2, message = "是否用印长度不能超过2个字符")
    @ApiModelProperty(value = "是否用印", name = "ynSeal")
    @FieldRemark(column="YN_SEAL", field="ynSeal", name="是否用印")
    private String ynSeal;

    /**
    * 是否用印中文
    */
    @Size(max = 10, message = "是否用印中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否用印中文", name = "ynSealZhmc")
    @FieldRemark(column="YN_SEAL_ZHMC", field="ynSealZhmc", name="是否用印中文")
    private String ynSealZhmc;

    /**
    * 是否框架合同
    */
    @Size(max = 2, message = "是否框架合同长度不能超过2个字符")
    @ApiModelProperty(value = "是否框架合同", name = "ynFramework")
    @FieldRemark(column="YN_FRAMEWORK", field="ynFramework", name="是否框架合同")
    private String ynFramework;

    /**
    * 是否框架合同中文
    */
    @Size(max = 10, message = "是否框架合同中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否框架合同中文", name = "ynFrameworkZhmc")
    @FieldRemark(column="YN_FRAMEWORK_ZHMC", field="ynFrameworkZhmc", name="是否框架合同中文")
    private String ynFrameworkZhmc;

    /**
    * 是否有下游合同付款周期约定
    */
    @Size(max = 2, message = "是否有下游合同付款周期约定长度不能超过2个字符")
    @ApiModelProperty(value = "是否有下游合同付款周期约定", name = "ynAgreedCycle")
    @FieldRemark(column="YN_AGREED_CYCLE", field="ynAgreedCycle", name="是否有下游合同付款周期约定")
    private String ynAgreedCycle;

    /**
    * 是否有下游合同付款周期约定中文
    */
    @Size(max = 10, message = "是否有下游合同付款周期约定中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否有下游合同付款周期约定中文", name = "ynAgreedCycleZhmc")
    @FieldRemark(column="YN_AGREED_CYCLE_ZHMC", field="ynAgreedCycleZhmc", name="是否有下游合同付款周期约定中文")
    private String ynAgreedCycleZhmc;

    /**
    * 是否军方直签
    */
    @Size(max = 2, message = "是否军方直签长度不能超过2个字符")
    @ApiModelProperty(value = "是否军方直签", name = "ynMilitaryDirectsigning")
    @FieldRemark(column="YN_MILITARY_DIRECTSIGNING", field="ynMilitaryDirectsigning", name="是否军方直签")
    private String ynMilitaryDirectsigning;

    /**
    * 是否军方直签中文
    */
    @Size(max = 10, message = "是否军方直签中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否军方直签中文", name = "ynMilitaryDirectsigningZhmc")
    @FieldRemark(column="YN_MILITARY_DIRECTSIGNING_ZHMC", field="ynMilitaryDirectsigningZhmc", name="是否军方直签中文")
    private String ynMilitaryDirectsigningZhmc;

    /**
    * 是否军方监管
    */
    @Size(max = 2, message = "是否军方监管长度不能超过2个字符")
    @ApiModelProperty(value = "是否军方监管", name = "ynMilitarySupervise")
    @FieldRemark(column="YN_MILITARY_SUPERVISE", field="ynMilitarySupervise", name="是否军方监管")
    private String ynMilitarySupervise;

    /**
    * 军方监管单位
    */
    @Size(max = 200, message = "军方监管单位长度不能超过200个字符")
    @ApiModelProperty(value = "军方监管单位", name = "ynMilitarySuperviseCompany")
    @FieldRemark(column="YN_MILITARY_SUPERVISE_COMPANY", field="ynMilitarySuperviseCompany", name="军方监管单位")
    private String ynMilitarySuperviseCompany;

    /**
    * 是否PLDG合同
    */
    @Size(max = 2, message = "是否PLDG合同长度不能超过2个字符")
    @ApiModelProperty(value = "是否PLDG合同", name = "ynPldgCm")
    @FieldRemark(column="YN_PLDG_CM", field="ynPldgCm", name="是否PLDG合同")
    private String ynPldgCm;

    /**
    * 是否PLDG合同中文
    */
    @Size(max = 10, message = "是否PLDG合同中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否PLDG合同中文", name = "ynPldgCmZhmc")
    @FieldRemark(column="YN_PLDG_CM_ZHMC", field="ynPldgCmZhmc", name="是否PLDG合同中文")
    private String ynPldgCmZhmc;

    /**
    * 是否暂定价格
    */
    @Size(max = 2, message = "是否暂定价格长度不能超过2个字符")
    @ApiModelProperty(value = "是否暂定价格", name = "ynZdPrice")
    @FieldRemark(column="YN_ZD_PRICE", field="ynZdPrice", name="是否暂定价格")
    private String ynZdPrice;

    /**
    * 是否暂定价格中文
    */
    @Size(max = 10, message = "是否暂定价格中文长度不能超过10个字符")
    @ApiModelProperty(value = "是否暂定价格中文", name = "ynZdPriceZhmc")
    @FieldRemark(column="YN_ZD_PRICE_ZHMC", field="ynZdPriceZhmc", name="是否暂定价格中文")
    private String ynZdPriceZhmc;

    /**
    * 是否签订订单
    */
    @Size(max = 2, message = "是否签订订单长度不能超过2个字符")
    @ApiModelProperty(value = "是否签订订单", name = "ynSignOrder")
    @FieldRemark(column="YN_SIGN_ORDER", field="ynSignOrder", name="是否签订订单")
    private String ynSignOrder;
    /**
    * 合同暂定价格
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "合同暂定价格格式不正确")
    @ApiModelProperty(value = "合同暂定价格", name = "cmZdPrice")
    @FieldRemark(column="CM_ZD_PRICE", field="cmZdPrice", name="合同暂定价格")
    private java.math.BigDecimal cmZdPrice;

    /**
    * 结算方式
    */
    @Size(max = 50, message = "结算方式长度不能超过50个字符")
    @ApiModelProperty(value = "结算方式", name = "settlementMethod")
    @FieldRemark(column="SETTLEMENT_METHOD", field="settlementMethod", name="结算方式")
    private String settlementMethod;
    /**
    * 结算增减比率(%)
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 10, fraction = 2, message = "结算增减比率(%)格式不正确")
    @ApiModelProperty(value = "结算增减比率(%)", name = "settlementZjBl")
    @FieldRemark(column="SETTLEMENT_ZJ_BL", field="settlementZjBl", name="结算增减比率(%)")
    private java.math.BigDecimal settlementZjBl;
    /**
    * 结算增减额（上限）（元）
    */
    @NumberFormat(pattern = "0.00")
    @Digits(integer = 20, fraction = 2, message = "结算增减额（上限）（元）格式不正确")
    @ApiModelProperty(value = "结算增减额（上限）（元）", name = "settlementZjMoney")
    @FieldRemark(column="SETTLEMENT_ZJ_MONEY", field="settlementZjMoney", name="结算增减额（上限）（元）")
    private java.math.BigDecimal settlementZjMoney;

    /**
    * 电子平台采购
    */
    @Size(max = 200, message = "电子平台采购长度不能超过200个字符")
    @ApiModelProperty(value = "电子平台采购", name = "electronicPlatform")
    @FieldRemark(column="ELECTRONIC_PLATFORM", field="electronicPlatform", name="电子平台采购")
    private String electronicPlatform;

    /**
    * 外贸代理公司
    */
    @Size(max = 500, message = "外贸代理公司长度不能超过500个字符")
    @ApiModelProperty(value = "外贸代理公司", name = "internetRemark")
    @FieldRemark(column="INTERNET_REMARK", field="internetRemark", name="外贸代理公司")
    private String internetRemark;

    /**
    * 科研合同履行期限
    */
    @Size(max = 500, message = "科研合同履行期限长度不能超过500个字符")
    @ApiModelProperty(value = "科研合同履行期限", name = "otherFeeRemark")
    @FieldRemark(column="OTHER_FEE_REMARK", field="otherFeeRemark", name="科研合同履行期限")
    private String otherFeeRemark;

    /**
    * 集团业务归口部门
    */
    @Size(max = 200, message = "集团业务归口部门长度不能超过200个字符")
    @ApiModelProperty(value = "集团业务归口部门", name = "groupBusputunderDept")
    @FieldRemark(column="GROUP_BUSPUTUNDER_DEPT", field="groupBusputunderDept", name="集团业务归口部门")
    private String groupBusputunderDept;

    /**
    * 对应我方收入类合同
    */
    @Size(max = 50, message = "对应我方收入类合同长度不能超过50个字符")
    @ApiModelProperty(value = "对应我方收入类合同", name = "weContractSrType")
    @FieldRemark(column="WE_CONTRACT_SR_TYPE", field="weContractSrType", name="对应我方收入类合同")
    private String weContractSrType;

    /**
    * 文本份数
    */
    @Size(max = 50, message = "文本份数长度不能超过50个字符")
    @ApiModelProperty(value = "文本份数", name = "textNum")
    @FieldRemark(column="TEXT_NUM", field="textNum", name="文本份数")
    private String textNum;

    /**
    * 文本密级
    */
    @Size(max = 50, message = "文本密级长度不能超过50个字符")
    @ApiModelProperty(value = "文本密级", name = "textSecret")
    @FieldRemark(column="TEXT_SECRET", field="textSecret", name="文本密级")
    private String textSecret;

    /**
    * 文本密级中文
    */
    @Size(max = 50, message = "文本密级中文长度不能超过50个字符")
    @ApiModelProperty(value = "文本密级中文", name = "textSecretZhmc")
    @FieldRemark(column="TEXT_SECRET_ZHMC", field="textSecretZhmc", name="文本密级中文")
    private String textSecretZhmc;

    /**
    * 承办人工号
    */
    @Size(max = 50, message = "承办人工号长度不能超过50个字符")
    @ApiModelProperty(value = "承办人工号", name = "cmUserCode")
    @FieldRemark(column="CM_USER_CODE", field="cmUserCode", name="承办人工号")
    private String cmUserCode;

    /**
    * 编制日期
    */
    @Size(max = 20, message = "编制日期长度不能超过20个字符")
    @ApiModelProperty(value = "编制日期", name = "applyDate")
    @FieldRemark(column="APPLY_DATE", field="applyDate", name="编制日期")
    private String applyDate;

    /**
    * 合同承办人所在部门
    */
    @Size(max = 100, message = "合同承办人所在部门长度不能超过100个字符")
    @ApiModelProperty(value = "合同承办人所在部门", name = "applyDept")
    @FieldRemark(column="APPLY_DEPT", field="applyDept", name="合同承办人所在部门")
    private String applyDept;

    /**
    * 编制人ID
    */
    @Size(max = 64, message = "编制人ID长度不能超过64个字符")
    @ApiModelProperty(value = "编制人ID", name = "applyId")
    @FieldRemark(column="APPLY_ID", field="applyId", name="编制人ID")
    private String applyId;

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
    @FieldRemark(column="OPERATION_TYPE_", field="operationType", name="操作类型")
    private String operationType;

    /**
    * 变更ID
    */
    @Size(max = 64, message = "变更ID长度不能超过64个字符")
    @ApiModelProperty(value = "变更ID", name = "changId")
    @FieldRemark(column="CHANG_ID", field="changId", name="变更ID")
    private String changId;


    @Size(max = 11, message = "同步状态长度不能超过11个字符")
    @ApiModelProperty(value = "同步状态", name = "syncStatus")
    @FieldRemark(column="SYNC_STATUS", field="syncStatus", name="同步状态")
    private String syncStatus ="N";

    @Size(max = 64, message = "一级分类编码长度不能超过64个字符")
    @ApiModelProperty(value = "一级分类编码", name = "contractTypeId")
    @FieldRemark(column="CONTRACT_TYPE_ID", field="contractTypeId", name="一级分类编码")
    private String contractTypeId;

    @Size(max = 11, message = "合同类型编码长度不能超过11个字符")
    @ApiModelProperty(value = "合同类型编码", name = "jsType")
    @FieldRemark(column="JS_TYPE", field="jsType", name="合同类型编码")
    private String jsType;

    @Size(max = 32, message = "合同类型名称长度不能超过32个字符")
    @ApiModelProperty(value = "合同类型名称", name = "jsTypeName")
    @FieldRemark(column="JS_TYPE_NAME", field="jsTypeName", name="合同类型名称")
    private String jsTypeName;

    @Size(max = 32, message = "履约人名称长度不能超过32个字符")
    @ApiModelProperty(value = "履约人", name = "contractPerformer")
    @FieldRemark(column="CONTRACT_PERFORMER", field="contractPerformer", name="履约人")
    private String contractPerformer;

    @Size(max = 32, message = "履约人名称长度不能超过32个字符")
    @ApiModelProperty(value = "履约人", name = "contractPerformerCode")
    @FieldRemark(column="CONTRACT_PERFORMER_CODE", field="contractPerformerCode", name="履约人编码")
    private String contractPerformerCode;

    @Size(max = 32, message = "合同类型名称长度不能超过32个字符")
    @ApiModelProperty(value = "履约人编码", name = "lyUserCode")
    @FieldRemark(column="LY_USER_CODE", field="lyUserCode", name="履约人编码")
    private String lyUserCode;

    @Size(max = 32, message = "履约人长度不能超过32个字符")
    @ApiModelProperty(value = "履约人", name = "lyUserName")
    @FieldRemark(column="LY_USER_NAME", field="lyUserName", name="履约人")
    private String lyUserName;


    public String getContractPerformer() {
        return contractPerformer;
    }

    public void setContractPerformer(String contractPerformer) {
        this.contractPerformer = contractPerformer;
    }

    public String getContractPerformerCode() {
        return contractPerformerCode;
    }

    public void setContractPerformerCode(String contractPerformerCode) {
        this.contractPerformerCode = contractPerformerCode;
    }

    public String getLyUserCode() {
        return lyUserCode;
    }

    public void setLyUserCode(String lyUserCode) {
        this.lyUserCode = lyUserCode;
    }

    public String getLyUserName() {
        return lyUserName;
    }

    public void setLyUserName(String lyUserName) {
        this.lyUserName = lyUserName;
    }

    public String getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType;
    }

    public String getJsTypeName() {
        return jsTypeName;
    }

    public void setJsTypeName(String jsTypeName) {
        this.jsTypeName = jsTypeName;
    }

    /**
     * 合同签约信息
     */
    private List<CmjtSignInfoDTO> cmSignInfoList;

    /**
     * 合同标的物信息
     */
    private List<CmjtSubjectInfoDTO> cmSubject064BdList;

    /**
     * 合同收付款计划信息
     */
    private List<CmjtPayReceInfoDTO> cmSubject102BdList;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getContractNo(){
        return contractNo;
    }

    public void setContractNo(String contractNo){
        this.contractNo = contractNo;
    }

    public String getContractName(){
        return contractName;
    }

    public void setContractName(String contractName){
        this.contractName = contractName;
    }

    public String getContractState(){
        return contractState;
    }

    public void setContractState(String contractState){
        this.contractState = contractState;
    }

    public java.math.BigDecimal getContractMoney(){
        return contractMoney;
    }

    public void setContractMoney(java.math.BigDecimal contractMoney){
        this.contractMoney = contractMoney;
    }

    public String getContractMoneyUpper(){
        return contractMoneyUpper;
    }

    public void setContractMoneyUpper(String contractMoneyUpper){
        this.contractMoneyUpper = contractMoneyUpper;
    }

    public java.math.BigDecimal getContractWbMoney(){
        return contractWbMoney;
    }

    public void setContractWbMoney(java.math.BigDecimal contractWbMoney){
        this.contractWbMoney = contractWbMoney;
    }

    public String getContractClassifyName(){
        return contractClassifyName;
    }

    public void setContractClassifyName(String contractClassifyName){
        this.contractClassifyName = contractClassifyName;
    }

    public String getContractClasstypeName(){
        return contractClasstypeName;
    }

    public void setContractClasstypeName(String contractClasstypeName){
        this.contractClasstypeName = contractClasstypeName;
    }

    public String getContractEstimateBegin(){
        return contractEstimateBegin;
    }

    public void setContractEstimateBegin(String contractEstimateBegin){
        this.contractEstimateBegin = contractEstimateBegin;
    }

    public String getContractEstimateEnd(){
        return contractEstimateEnd;
    }

    public void setContractEstimateEnd(String contractEstimateEnd){
        this.contractEstimateEnd = contractEstimateEnd;
    }

    public String getContractingParties(){
        return contractingParties;
    }

    public void setContractingParties(String contractingParties){
        this.contractingParties = contractingParties;
    }

    public String getCmdOrder(){
        return cmdOrder;
    }

    public void setCmdOrder(String cmdOrder){
        this.cmdOrder = cmdOrder;
    }

    public String getBusinessArea(){
        return businessArea;
    }

    public void setBusinessArea(String businessArea){
        this.businessArea = businessArea;
    }

    public String getAttribute02(){
        return attribute02;
    }

    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }

    public String getMilitaryCmType(){
        return militaryCmType;
    }

    public void setMilitaryCmType(String militaryCmType){
        this.militaryCmType = militaryCmType;
    }

    public String getScientificCmType(){
        return scientificCmType;
    }

    public void setScientificCmType(String scientificCmType){
        this.scientificCmType = scientificCmType;
    }

    public String getItemSecret(){
        return itemSecret;
    }

    public void setItemSecret(String itemSecret){
        this.itemSecret = itemSecret;
    }

    public String getProjectCode(){
        return projectCode;
    }

    public void setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }

    public String getProjectAbbreviation(){
        return projectAbbreviation;
    }

    public void setProjectAbbreviation(String projectAbbreviation){
        this.projectAbbreviation = projectAbbreviation;
    }

    public String getProjectType(){
        return projectType;
    }

    public void setProjectType(String projectType){
        this.projectType = projectType;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrencyZhmc(){
        return currencyZhmc;
    }

    public void setCurrencyZhmc(String currencyZhmc){
        this.currencyZhmc = currencyZhmc;
    }

    public java.math.BigDecimal getExchangeRate(){
        return exchangeRate;
    }

    public void setExchangeRate(java.math.BigDecimal exchangeRate){
        this.exchangeRate = exchangeRate;
    }

    public String getMultiContract(){
        return multiContract;
    }

    public void setMultiContract(String multiContract){
        this.multiContract = multiContract;
    }

    public String getSignOrg(){
        return signOrg;
    }

    public void setSignOrg(String signOrg){
        this.signOrg = signOrg;
    }

    public String getSignOrgName(){
        return signOrgName;
    }

    public void setSignOrgName(String signOrgName){
        this.signOrgName = signOrgName;
    }

    public String getUnifiedSocialCreditCode(){
        return unifiedSocialCreditCode;
    }

    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode){
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    public String getSupplierName(){
        return supplierName;
    }

    public void setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }

    public String getSupplierContactNo(){
        return supplierContactNo;
    }

    public void setSupplierContactNo(String supplierContactNo){
        this.supplierContactNo = supplierContactNo;
    }

    public String getPurchaseMode(){
        return purchaseMode;
    }

    public void setPurchaseMode(String purchaseMode){
        this.purchaseMode = purchaseMode;
    }

    public String getSealType(){
        return sealType;
    }

    public void setSealType(String sealType){
        this.sealType = sealType;
    }

    public String getSealTypeName(){
        return sealTypeName;
    }

    public void setSealTypeName(String sealTypeName){
        this.sealTypeName = sealTypeName;
    }

    public String getYnSeal(){
        return ynSeal;
    }

    public void setYnSeal(String ynSeal){
        this.ynSeal = ynSeal;
    }

    public String getYnSealZhmc(){
        return ynSealZhmc;
    }

    public void setYnSealZhmc(String ynSealZhmc){
        this.ynSealZhmc = ynSealZhmc;
    }

    public String getYnFramework(){
        return ynFramework;
    }

    public void setYnFramework(String ynFramework){
        this.ynFramework = ynFramework;
    }

    public String getYnFrameworkZhmc(){
        return ynFrameworkZhmc;
    }

    public void setYnFrameworkZhmc(String ynFrameworkZhmc){
        this.ynFrameworkZhmc = ynFrameworkZhmc;
    }

    public String getYnAgreedCycle(){
        return ynAgreedCycle;
    }

    public void setYnAgreedCycle(String ynAgreedCycle){
        this.ynAgreedCycle = ynAgreedCycle;
    }

    public String getYnAgreedCycleZhmc(){
        return ynAgreedCycleZhmc;
    }

    public void setYnAgreedCycleZhmc(String ynAgreedCycleZhmc){
        this.ynAgreedCycleZhmc = ynAgreedCycleZhmc;
    }

    public String getYnMilitaryDirectsigning(){
        return ynMilitaryDirectsigning;
    }

    public void setYnMilitaryDirectsigning(String ynMilitaryDirectsigning){
        this.ynMilitaryDirectsigning = ynMilitaryDirectsigning;
    }

    public String getYnMilitaryDirectsigningZhmc(){
        return ynMilitaryDirectsigningZhmc;
    }

    public void setYnMilitaryDirectsigningZhmc(String ynMilitaryDirectsigningZhmc){
        this.ynMilitaryDirectsigningZhmc = ynMilitaryDirectsigningZhmc;
    }

    public String getYnMilitarySupervise(){
        return ynMilitarySupervise;
    }

    public void setYnMilitarySupervise(String ynMilitarySupervise){
        this.ynMilitarySupervise = ynMilitarySupervise;
    }

    public String getYnMilitarySuperviseCompany(){
        return ynMilitarySuperviseCompany;
    }

    public void setYnMilitarySuperviseCompany(String ynMilitarySuperviseCompany){
        this.ynMilitarySuperviseCompany = ynMilitarySuperviseCompany;
    }

    public String getYnPldgCm(){
        return ynPldgCm;
    }

    public void setYnPldgCm(String ynPldgCm){
        this.ynPldgCm = ynPldgCm;
    }

    public String getYnPldgCmZhmc(){
        return ynPldgCmZhmc;
    }

    public void setYnPldgCmZhmc(String ynPldgCmZhmc){
        this.ynPldgCmZhmc = ynPldgCmZhmc;
    }

    public String getYnZdPrice(){
        return ynZdPrice;
    }

    public void setYnZdPrice(String ynZdPrice){
        this.ynZdPrice = ynZdPrice;
    }

    public String getYnZdPriceZhmc(){
        return ynZdPriceZhmc;
    }

    public void setYnZdPriceZhmc(String ynZdPriceZhmc){
        this.ynZdPriceZhmc = ynZdPriceZhmc;
    }

    public String getYnSignOrder(){
        return ynSignOrder;
    }

    public void setYnSignOrder(String ynSignOrder){
        this.ynSignOrder = ynSignOrder;
    }

    public java.math.BigDecimal getCmZdPrice(){
        return cmZdPrice;
    }

    public void setCmZdPrice(java.math.BigDecimal cmZdPrice){
        this.cmZdPrice = cmZdPrice;
    }

    public String getSettlementMethod(){
        return settlementMethod;
    }

    public void setSettlementMethod(String settlementMethod){
        this.settlementMethod = settlementMethod;
    }

    public java.math.BigDecimal getSettlementZjBl(){
        return settlementZjBl;
    }

    public void setSettlementZjBl(java.math.BigDecimal settlementZjBl){
        this.settlementZjBl = settlementZjBl;
    }

    public java.math.BigDecimal getSettlementZjMoney(){
        return settlementZjMoney;
    }

    public void setSettlementZjMoney(java.math.BigDecimal settlementZjMoney){
        this.settlementZjMoney = settlementZjMoney;
    }

    public String getElectronicPlatform(){
        return electronicPlatform;
    }

    public void setElectronicPlatform(String electronicPlatform){
        this.electronicPlatform = electronicPlatform;
    }

    public String getInternetRemark(){
        return internetRemark;
    }

    public void setInternetRemark(String internetRemark){
        this.internetRemark = internetRemark;
    }

    public String getOtherFeeRemark(){
        return otherFeeRemark;
    }

    public void setOtherFeeRemark(String otherFeeRemark){
        this.otherFeeRemark = otherFeeRemark;
    }

    public String getGroupBusputunderDept(){
        return groupBusputunderDept;
    }

    public void setGroupBusputunderDept(String groupBusputunderDept){
        this.groupBusputunderDept = groupBusputunderDept;
    }

    public String getWeContractSrType(){
        return weContractSrType;
    }

    public void setWeContractSrType(String weContractSrType){
        this.weContractSrType = weContractSrType;
    }

    public String getTextNum(){
        return textNum;
    }

    public void setTextNum(String textNum){
        this.textNum = textNum;
    }

    public String getTextSecret(){
        return textSecret;
    }

    public void setTextSecret(String textSecret){
        this.textSecret = textSecret;
    }

    public String getTextSecretZhmc(){
        return textSecretZhmc;
    }

    public void setTextSecretZhmc(String textSecretZhmc){
        this.textSecretZhmc = textSecretZhmc;
    }

    public String getCmUserCode(){
        return cmUserCode;
    }

    public void setCmUserCode(String cmUserCode){
        this.cmUserCode = cmUserCode;
    }

    public String getApplyDate(){
        return applyDate;
    }

    public void setApplyDate(String applyDate){
        this.applyDate = applyDate;
    }

    public String getApplyDept(){
        return applyDept;
    }

    public void setApplyDept(String applyDept){
        this.applyDept = applyDept;
    }

    public String getApplyId(){
        return applyId;
    }

    public void setApplyId(String applyId){
        this.applyId = applyId;
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

    public String getChangId(){
        return changId;
    }

    public void setChangId(String changId){
        this.changId = changId;
    }

    public List<CmjtSignInfoDTO> getCmSignInfoList(){
        return cmSignInfoList;
    }

    public void setCmSignInfoList(List<CmjtSignInfoDTO> cmSignInfoList){
        this.cmSignInfoList = cmSignInfoList;
    }


    public List<CmjtSubjectInfoDTO> getCmSubject064BdList(){
        return cmSubject064BdList;
    }

    public void setCmSubject064BdList(List<CmjtSubjectInfoDTO> cmSubject064BdList){
        this.cmSubject064BdList = cmSubject064BdList;
    }

    public List<CmjtPayReceInfoDTO> getCmSubject102BdList(){
        return cmSubject102BdList;
    }

    public void setCmSubject102BdList(List<CmjtPayReceInfoDTO> cmSubject102BdList){
        this.cmSubject102BdList = cmSubject102BdList;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "合同主表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "合同主表";
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
