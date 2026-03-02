package avicit.cmjt.cmjtcontract.rest;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractRequstParamDTO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractResposeDTO;
import avicit.cmjt.cmjtcontract.service.CmjtContractService;
import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.cmjt.cmjtpayreceinfo.service.CmjtPayReceInfoService;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsigninfo.service.CmjtSignInfoService;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.service.CmjtSubjectInfoService;
import avicit.cmjt.task.service.CmSyncService;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-11 11:31
 * @类说明：合同主表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtContract", value = "合同主表")
@RequestMapping("/api/la/cmjt/cmjtcontracts")
public class CmjtContractRest {

	private static final Logger logger = LoggerFactory.getLogger(CmjtContractRest.class);

	@Autowired
	private CmjtContractService cmjtContractService;

	@Autowired
	private CmSyncService cmSyncService;

	@Autowired
	private CmjtPayReceInfoService cmjtPayReceInfoService;
	@Autowired
	private CmjtSignInfoService cmjtSignInfoService;
	@Autowired
	private CmjtSubjectInfoService subjectInfoService;

	/**
	 * 按条件分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list-by-page/v1")
	@ApiOperation(value = "按条件分页查询")
	public ResponseMsg<QueryRespBean<CmjtContractDTO>> findListByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtContractDTO> queryReqBean) {
		ResponseMsg<QueryRespBean<CmjtContractDTO>> responseMsg = new ResponseMsg<>();
		QueryRespBean<CmjtContractDTO> result = cmjtContractService.findListByPage(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	@PostMapping("/query-list-by-date/v1")
	@ApiOperation(value = "按时间范围查询合同列表", notes = "根据开始时间和结束时间字符串查询合同")
	public ResponseMsg<List<CmjtContractResposeDTO>> queryListByPage(
			@ApiParam(value = "查询条件", required = true) @RequestBody CmjtContractRequstParamDTO queryReqBean) {

		// 参数校验
		validateRequestParams(queryReqBean);

		// 批量查询主数据
		List<CmjtContractResposeDTO> contracts = cmjtContractService.selectCmjtContractByDate(
				queryReqBean.getStartTime(),
				queryReqBean.getEndTime()
		);

		// 无数据时直接返回空列表
		if (contracts.isEmpty()) {
			return new ResponseMsg<>();
		}

		// 批量加载关联数据
		enrichContractDetails(contracts);
		ResponseMsg<List<CmjtContractResposeDTO>> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(contracts);
		return responseMsg;
	}

	// 参数校验封装
	private void validateRequestParams(CmjtContractRequstParamDTO params) {
		if (StringUtils.isBlank(params.getStartTime()) || StringUtils.isBlank(params.getEndTime())) {
			throw new IllegalArgumentException("开始时间和结束时间不能为空");
		}
		validateDateFormat(params.getStartTime());
		validateDateFormat(params.getEndTime());
	}

	// 批量关联数据
	private void enrichContractDetails(List<CmjtContractResposeDTO> contracts) {
		// 获取合同ID集合
		List<String> contractIds = contracts.stream()
				.map(CmjtContractResposeDTO::getId)
				.collect(Collectors.toList());

		// 并行批量查询
		Map<String, List<CmjtPayReceInfoDTO>> paymentsMap = batchLoadPayments(contractIds);
		Map<String, List<CmjtSignInfoDTO>> signsMap = batchLoadSigns(contractIds);
		Map<String, List<CmjtSubjectInfoDTO>> subjectsMap = batchLoadSubjects(contractIds);

		// 关联数据装配
		contracts.forEach(contract -> {
			contract.setCmjtPayReceInfoDTOList(paymentsMap.getOrDefault(contract.getId(), Collections.emptyList()));
			contract.setCmjtSignInfoDTOList(signsMap.getOrDefault(contract.getId(), Collections.emptyList()));
			contract.setCmjtSubjectInfoDTOList(subjectsMap.getOrDefault(contract.getId(), Collections.emptyList()));
		});
	}

	// 批量付款查询
	private Map<String, List<CmjtPayReceInfoDTO>> batchLoadPayments(List<String> contractIds) {
		List<CmjtPayReceInfoDTO> payments = cmjtPayReceInfoService.selectByContractIds(contractIds);
		return payments.stream()
				.collect(Collectors.groupingBy(CmjtPayReceInfoDTO::getZbid));
	}
	// 批量签约查询
	private Map<String, List<CmjtSignInfoDTO>> batchLoadSigns(List<String> contractIds) {
		List<CmjtSignInfoDTO> payments = cmjtSignInfoService.selectByContractIds(contractIds);
		return payments.stream()
				.collect(Collectors.groupingBy(CmjtSignInfoDTO::getContractId));
	}
	// 批量标的查询
	private Map<String, List<CmjtSubjectInfoDTO>> batchLoadSubjects(List<String> contractIds) {
		List<CmjtSubjectInfoDTO> payments = subjectInfoService.selectByContractIds(contractIds);
		return payments.stream()
				.collect(Collectors.groupingBy(CmjtSubjectInfoDTO::getZbid));
	}

	// 简单的日期格式校验
	private void validateDateFormat(String dateStr) {
		if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new IllegalArgumentException("日期格式必须为yyyy-MM-dd");
		}
	}
	/**
	 * 请求集团接口获取集团合同数据
	 */
	@PostMapping("/post-cmjt/v1")
	public void postCmjt() {
//		cmjtContractService.postCmjtContract();
		cmSyncService.syncCmLedgerData("cmContract");
	}

}
