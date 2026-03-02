package avicit.cmjt.cmjtsigninfo.rest;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsigninfo.service.CmjtSignInfoService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-16 10:44
 * @类说明：合同签约信息表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtSignInfo", value = "合同签约信息表")
@RequestMapping("/api/la/cmjt/cmjtsigninfos")
	public class CmjtSignInfoRest {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSignInfoRest.class);

	@Autowired
	private CmjtSignInfoService cmjtSignInfoService;

	/**
	 * 按条件分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list-by-page/v1")
	@ApiOperation(value = "按条件分页查询")
	public ResponseMsg<QueryRespBean<CmjtSignInfoDTO>> findListByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtSignInfoDTO> queryReqBean) {
		ResponseMsg<QueryRespBean<CmjtSignInfoDTO>> responseMsg = new ResponseMsg<>();
		QueryRespBean<CmjtSignInfoDTO> result = cmjtSignInfoService.findListByPage(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 按条件不分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<List<CmjtSignInfoDTO>>
	 */
	@PostMapping("/find-list/v1")
	@ApiOperation(value = "按条件不分页查询")
	public ResponseMsg<List<CmjtSignInfoDTO>> findList(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtSignInfoDTO> queryReqBean) {
		ResponseMsg<List<CmjtSignInfoDTO>> responseMsg = new ResponseMsg<>();
		List<CmjtSignInfoDTO> result = cmjtSignInfoService.findList(queryReqBean);
		responseMsg.setResponseBody(result);
        SysLogUtil.log("合同签约信息表", "查询合同签约信息表信息", PlatformConstant.OpType.select);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id 主键id
	 * @return ResponseMsg<CmjtSignInfoDTO>
	 */
	@GetMapping("/get/{id}/v1")
	@ApiOperation(value = "通过主键查询单条记录")
	public ResponseMsg<CmjtSignInfoDTO> get(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
		ResponseMsg<CmjtSignInfoDTO> responseMsg = new ResponseMsg<>();
		CmjtSignInfoDTO cmjtSignInfo = cmjtSignInfoService.get(id);
		responseMsg.setResponseBody(cmjtSignInfo);
		SysLogUtil.log4Query(cmjtSignInfo);
		return responseMsg;
	}

	/**
	 * 保存对象
	 *
	 * @param cmjtSignInfo 保存对象
	 * @return ResponseMsg<String>
	 */
	@PostMapping("/save/v1")
	@ApiOperation(value = "新增对象")
	public ResponseMsg<String> save(@ApiParam(value = "保存对象", name = "cmjtSignInfo") @Validated @RequestBody CmjtSignInfoDTO cmjtSignInfo) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<>();
		if (StringUtils.isEmpty(cmjtSignInfo.getId())) {
			responseMsg.setResponseBody(cmjtSignInfoService.insert(cmjtSignInfo));
		}else{
			responseMsg.setResponseBody(String.valueOf(cmjtSignInfoService.updateAll(cmjtSignInfo)));
		}
		return responseMsg;
	}

    /**
	 * 修改部分对象字段
	 *
	 * @param cmjtSignInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-sensitive/v1")
	@ApiOperation(value = "修改部分对象字段")
	public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "修改对象", name = "cmjtSignInfo")  @RequestBody CmjtSignInfoDTO cmjtSignInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSignInfoService.updateSensitive(cmjtSignInfo));
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 *
	 * @param cmjtSignInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-all/v1")
	@ApiOperation(value = "修改全部对象字段")
	public ResponseMsg<Integer> updateAll(@ApiParam(value = "修改对象", name = "cmjtSignInfo") @Validated @RequestBody CmjtSignInfoDTO cmjtSignInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSignInfoService.updateAll(cmjtSignInfo));
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
		responseMsg.setResponseBody(cmjtSignInfoService.delete(id));
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
	@RequiresPermissions("cmjtSignInfo:delete")
	public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody String[] ids) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSignInfoService.deleteByIds(ids));
		return responseMsg;
	}

}
