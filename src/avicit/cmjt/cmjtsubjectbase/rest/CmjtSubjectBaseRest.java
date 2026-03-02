package avicit.cmjt.cmjtsubjectbase.rest;

import avicit.cmjt.cmjtsubjectbase.dto.CmjtSubjectBaseDTO;
import avicit.cmjt.cmjtsubjectbase.service.CmjtSubjectBaseService;
import avicit.cmjt.task.service.CmSyncService;
import avicit.platform6.core.rest.msg.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-11 09:57
 * @类说明：标的物库表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtSubjectBase", value = "标的物库表")
@RequestMapping("/api/la/cmjt/cmjtsubjectbases")
	public class CmjtSubjectBaseRest {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSubjectBaseRest.class);

	@Autowired
	private CmjtSubjectBaseService cmjtSubjectBaseService;

	@Autowired
	private CmSyncService cmSyncService;

	/**
	 * 批量新增
	 * @param list
	 * @throws Exception
	 */
	@PostMapping({"/insert-subject-by-batch/v1"})
	@ApiOperation("批量新增")
	public ResponseMsg<Integer> insertBatch(@ApiParam(value = "新增数据集合",name = "list") @RequestBody List<CmjtSubjectBaseDTO> list) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		try {
			responseMsg.setResponseBody(cmjtSubjectBaseService.insertBatch(list));
		}catch (Exception e){
			responseMsg.setResponseBody(0);
			responseMsg.setRetCode("500");
			responseMsg.setErrorDesc("批量新增失败"+e.getMessage());
		}
		return responseMsg;
	}

	/**
	 * 批量修改
	 * @param list
	 * @throws Exception
	 */
	@PostMapping({"/update-subject-by-batch/v1"})
	@ApiOperation("批量更新")
	public ResponseMsg<Integer> updateBatch(@ApiParam(value = "修改数据集合",name = "list") @RequestBody List<CmjtSubjectBaseDTO> list) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		try {
			responseMsg.setResponseBody(cmjtSubjectBaseService.updateBatch(list));
		}catch (Exception e){
			responseMsg.setResponseBody(0);
			responseMsg.setRetCode("500");
			responseMsg.setErrorDesc("批量更新失败"+e.getMessage());
		}
		return responseMsg;
	}

	/**
	 * 批量删除
	 *
	 * @param map 数据来源，逗号分隔的id串
	 * @return ResponseMsg<Integer>
	 */
	@DeleteMapping("/delete-by-ids/v1")
	@ApiOperation("批量删除")
	public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody Map<String, Object> map) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		try{
			String dataSource = (String) map.get("source");
			List<String> businessIds = (List<String>) map.get("ids");
			responseMsg.setResponseBody(cmjtSubjectBaseService.deleteBySourceAndBusinessIds(dataSource, businessIds));
		}catch (Exception e){
			responseMsg.setResponseBody(0);
			responseMsg.setRetCode("500");
			responseMsg.setErrorDesc("批量删除失败"+e.getMessage());
		}
		return responseMsg;
	}

//	/**
//	 * 按条件分页查询
//	 *
//	 * @param queryReqBean 查询条件
//	 * @return ResponseMsg<QueryRespBean<CmjtSubjectBaseDTO>>
//	 */
//	@PostMapping("/find-list-by-page/v1")
//	@ApiOperation(value = "按条件分页查询")
//	public ResponseMsg<QueryRespBean<CmjtSubjectBaseDTO>> findListByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<CmjtSubjectBaseDTO> queryReqBean) {
//		ResponseMsg<QueryRespBean<CmjtSubjectBaseDTO>> responseMsg = new ResponseMsg<>();
//		QueryRespBean<CmjtSubjectBaseDTO> result = cmjtSubjectBaseService.findListByPage(queryReqBean);
//		responseMsg.setResponseBody(result);
//		return responseMsg;
//	}

	/**
	 * 按条件不分页查询
	 *
	 * @param cmjtSubjectBaseDTO 查询条件
	 * @return ResponseMsg<List<CmjtSubjectInfoDTO>>
	 */
	@PostMapping("/find-list/v1")
	@ApiOperation(value = "按条件不分页查询")
	public ResponseMsg<List<CmjtSubjectBaseDTO>> findList(@ApiParam(value = "查询条件", name = "cmjtSubjectBaseDTO") @RequestBody CmjtSubjectBaseDTO cmjtSubjectBaseDTO) {
		ResponseMsg<List<CmjtSubjectBaseDTO>> responseMsg = new ResponseMsg<>();
		try{
			List<CmjtSubjectBaseDTO> result = cmjtSubjectBaseService.findList(cmjtSubjectBaseDTO);
			responseMsg.setResponseBody(result);
		}catch (Exception e){
			responseMsg.setResponseBody(null);
			responseMsg.setRetCode("500");
			responseMsg.setErrorDesc("失败"+e.getMessage());
		}
		return responseMsg;
	}

	/**
	 * 请求集团接口获取集团合同数据
	 */
	@PostMapping("/post-cmjt-subject/v1")
	public void postCmjtSubject() {
		cmSyncService.syncCmjtSubjectBaseData("cmjtSubjectBase");
	}

}
