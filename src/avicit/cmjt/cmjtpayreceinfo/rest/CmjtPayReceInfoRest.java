package avicit.cmjt.cmjtpayreceinfo.rest;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.cmjt.cmjtpayreceinfo.service.CmjtPayReceInfoService;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-16 10:47
 * @类说明：合同收付款计划信息表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtPayReceInfo", value = "合同收付款计划信息表")
@RequestMapping("/api/la/cmjt/cmjtpayreceinfos")
public class CmjtPayReceInfoRest {

	private static final Logger logger = LoggerFactory.getLogger(CmjtPayReceInfoRest.class);

	@Autowired
	private CmjtPayReceInfoService cmjtPayReceInfoService;

	/**
	 * 按条件分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list-by-page/v1")
	@ApiOperation(value = "按条件分页查询")
	public ResponseMsg<QueryRespBean<CmjtPayReceInfoDTO>> findListByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtPayReceInfoDTO> queryReqBean) {
		ResponseMsg<QueryRespBean<CmjtPayReceInfoDTO>> responseMsg = new ResponseMsg<>();
		QueryRespBean<CmjtPayReceInfoDTO> result = cmjtPayReceInfoService.findListByPage(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 按条件不分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<List<CmjtPayReceInfoDTO>>
	 */
	@PostMapping("/find-list/v1")
	@ApiOperation(value = "按条件不分页查询")
	public ResponseMsg<List<CmjtPayReceInfoDTO>> findList(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtPayReceInfoDTO> queryReqBean) {
		ResponseMsg<List<CmjtPayReceInfoDTO>> responseMsg = new ResponseMsg<>();
		List<CmjtPayReceInfoDTO> result = cmjtPayReceInfoService.findList(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id 主键id
	 * @return ResponseMsg<CmjtPayReceInfoDTO>
	 */
	@GetMapping("/get/{id}/v1")
	@ApiOperation(value = "通过主键查询单条记录")
	public ResponseMsg<CmjtPayReceInfoDTO> get(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
		ResponseMsg<CmjtPayReceInfoDTO> responseMsg = new ResponseMsg<>();
		CmjtPayReceInfoDTO cmjtPayReceInfo = cmjtPayReceInfoService.get(id);
		responseMsg.setResponseBody(cmjtPayReceInfo);
		SysLogUtil.log4Query(cmjtPayReceInfo);
		return responseMsg;
	}

	/**
	 * 保存对象
	 *
	 * @param cmjtPayReceInfo 保存对象
	 * @return ResponseMsg<String>
	 */
	@PostMapping("/save/v1")
	@ApiOperation(value = "新增对象")
	public ResponseMsg<String> save(@ApiParam(value = "保存对象", name = "cmjtPayReceInfo") @Validated @RequestBody CmjtPayReceInfoDTO cmjtPayReceInfo) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<>();
		if (StringUtils.isEmpty(cmjtPayReceInfo.getId())) {
			responseMsg.setResponseBody(String.valueOf(cmjtPayReceInfoService.insert(cmjtPayReceInfo)));
		}else{
			responseMsg.setResponseBody(String.valueOf(cmjtPayReceInfoService.updateAll(cmjtPayReceInfo)));
		}
		return responseMsg;
	}

    /**
	 * 修改部分对象字段
	 *
	 * @param cmjtPayReceInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-sensitive/v1")
	@ApiOperation(value = "修改部分对象字段")
	public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "修改对象", name = "cmjtPayReceInfo")  @RequestBody CmjtPayReceInfoDTO cmjtPayReceInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtPayReceInfoService.updateSensitive(cmjtPayReceInfo));
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 *
	 * @param cmjtPayReceInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-all/v1")
	@ApiOperation(value = "修改全部对象字段")
	public ResponseMsg<Integer> updateAll(@ApiParam(value = "修改对象", name = "cmjtPayReceInfo") @Validated @RequestBody CmjtPayReceInfoDTO cmjtPayReceInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtPayReceInfoService.updateAll(cmjtPayReceInfo));
		return responseMsg;
	}

	/**
	 * 按主键单条删除
	 *
	 * @param id 主键id
	 * @return ResponseMsg<Integer>
	 */
	@DeleteMapping("/delete-by-id/{id}/v1")
	@ApiOperation(value = "按主键单条删除")
	public ResponseMsg<Integer> deleteById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtPayReceInfoService.delete(id));
		return responseMsg;
	}

	/**
	 * 批量删除
	 *
	 * @param ids 逗号分隔的id串
	 * @return ResponseMsg<Integer>
	 */
	@DeleteMapping("/delete-by-ids/v1")
	@ApiOperation(value = "批量删除")
	public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody String[] ids) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtPayReceInfoService.deleteByIds(ids));
		return responseMsg;
	}

}
