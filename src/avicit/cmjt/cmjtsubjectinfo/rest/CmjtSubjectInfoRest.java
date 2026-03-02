package avicit.cmjt.cmjtsubjectinfo.rest;

import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.service.CmjtSubjectInfoService;
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

import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-16 10:44
 * @类说明：合同标的物信息表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtSubjectInfo", value = "合同标的物信息表")
@RequestMapping("/api/la/cmjt/cmjtsubjectinfos")
	public class CmjtSubjectInfoRest {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSubjectInfoRest.class);

	@Autowired
	private CmjtSubjectInfoService cmjtSubjectInfoService;

	/**
	 * 按条件分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list-by-page/v1")
	@ApiOperation(value = "按条件分页查询")
	public ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>> findListByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtSubjectInfoDTO> queryReqBean) {
		ResponseMsg<QueryRespBean<CmjtSubjectInfoDTO>> responseMsg = new ResponseMsg<>();
		QueryRespBean<CmjtSubjectInfoDTO> result = cmjtSubjectInfoService.findListByPage(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 按条件不分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<List<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list/v1")
	@ApiOperation(value = "按条件不分页查询")
	public ResponseMsg<List<CmjtSubjectInfoDTO>> findList(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtSubjectInfoDTO> queryReqBean) {
		ResponseMsg<List<CmjtSubjectInfoDTO>> responseMsg = new ResponseMsg<>();
		List<CmjtSubjectInfoDTO> result = cmjtSubjectInfoService.findList(queryReqBean);
		responseMsg.setResponseBody(result);
        SysLogUtil.log("合同标的物信息表", "查询合同标的物信息表信息", PlatformConstant.OpType.select);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id 主键id
	 * @return ResponseMsg<CmjtSubjectInfoDTO>
	 */
	@GetMapping("/get/{id}/v1")
	@ApiOperation(value = "通过主键查询单条记录")
	public ResponseMsg<CmjtSubjectInfoDTO> get(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
		ResponseMsg<CmjtSubjectInfoDTO> responseMsg = new ResponseMsg<>();
		CmjtSubjectInfoDTO cmjtSubjectInfo = cmjtSubjectInfoService.get(id);
		responseMsg.setResponseBody(cmjtSubjectInfo);
		SysLogUtil.log4Query(cmjtSubjectInfo);
		return responseMsg;
	}

	/**
	 * 保存对象
	 *
	 * @param cmjtSubjectInfo 保存对象
	 * @return ResponseMsg<String>
	 */
	@PostMapping("/save/v1")
	@ApiOperation(value = "新增对象")
	public ResponseMsg<String> save(@ApiParam(value = "保存对象", name = "cmjtSubjectInfo") @Validated @RequestBody CmjtSubjectInfoDTO cmjtSubjectInfo) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<>();
		if (StringUtils.isEmpty(cmjtSubjectInfo.getId())) {
			responseMsg.setResponseBody(String.valueOf(cmjtSubjectInfoService.insert(cmjtSubjectInfo)));
		}else{
			responseMsg.setResponseBody(String.valueOf(cmjtSubjectInfoService.updateAll(cmjtSubjectInfo)));
		}
		return responseMsg;
	}

    /**
	 * 修改部分对象字段
	 *
	 * @param cmjtSubjectInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-sensitive/v1")
	@ApiOperation(value = "修改部分对象字段")
	public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "修改对象", name = "cmjtSubjectInfo")  @RequestBody CmjtSubjectInfoDTO cmjtSubjectInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSubjectInfoService.updateSensitive(cmjtSubjectInfo));
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 *
	 * @param cmjtSubjectInfo 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-all/v1")
	@ApiOperation(value = "修改全部对象字段")
	public ResponseMsg<Integer> updateAll(@ApiParam(value = "修改对象", name = "cmjtSubjectInfo") @Validated @RequestBody CmjtSubjectInfoDTO cmjtSubjectInfo) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSubjectInfoService.updateAll(cmjtSubjectInfo));
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
		responseMsg.setResponseBody(cmjtSubjectInfoService.delete(id));
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
	@RequiresPermissions("cmjtSubjectInfo:delete")
	public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody String[] ids) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(cmjtSubjectInfoService.deleteByIds(ids));
		return responseMsg;
	}

}
