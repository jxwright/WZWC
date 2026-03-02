package avicit.cmjt.cmjtsubjectinfo.controller;

import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.service.CmjtSubjectInfoService;
import avicit.platform6.api.application.SysApplicationAPI;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-24 16:31
 * @类说明：CMJT_SUBJECT_INFOController
 * @修改记录： 
 */
@Controller
@Scope("prototype")
@RequestMapping("avicit/cmjt/cmjtSubjectInfo/cmjtSubjectInfoController")
public class CmjtSubjectInfoController implements LoaderConstant {
	private static final Logger LOGGER = LoggerFactory.getLogger(CmjtSubjectInfoController.class);

	@Autowired
	private SysApplicationAPI sysApplicationAPI;
	@Autowired
	private CmjtSubjectInfoService cmjtSubjectInfoService;

	/**
	 * 跳转到列表页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = "toCmjtSubjectInfoManage")
	public ModelAndView toCmjtSubjectInfoManage(@RequestParam("contractId") String contractId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/cmjt/cmjtsubjectinfo/CmjtSubjectInfoManage");
		mav.addObject("url", "platform/avicit/cmjt/cmjtSubjectInfo/cmjtSubjectInfoController/operation/");
		mav.addObject("pid",contractId);
		return mav;
	}

	/**
	 * 列表页面分页查询
	 * @param pageParameter 查询条件
	 * @param request 请求
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/operation/getCmjtSubjectInfosByPage")
	@ResponseBody
	public Map<String, Object> togetCmjtSubjectInfoByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<CmjtSubjectInfoDTO> queryReqBean = new QueryReqBean<CmjtSubjectInfoDTO>();
		queryReqBean.setPageParameter(pageParameter);
		//表单数据
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		//字段查询条件
		String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", "");
		//排序方式
		String sord = ServletRequestUtils.getStringParameter(request, "sord", "");
		//排序字段
		String sidx = ServletRequestUtils.getStringParameter(request, "sidx", "");
		if (StringUtils.isNotEmpty(keyWord)) {
			json = keyWord;
		}
		String orderBy = "";
		String cloumnName = "";
		if (sidx != null && sord != null && !"".equals(sord) && !"".equals(sidx)) {
			cloumnName = ComUtil.getColumn(CmjtSubjectInfoDTO.class, sidx);
			//这里先进行判断是否有对应的数据库字段
			if (cloumnName != null) {
				orderBy = " " + cloumnName + " " + sord;
			} else {
				//判断是否为特殊控件导致字段无法匹配
				if (sidx.indexOf("Alias") != -1) {
					cloumnName = ComUtil.getColumn(CmjtSubjectInfoDTO.class,
							sidx.substring(0, sidx.lastIndexOf("Alias")));
				} else if (sidx.indexOf("Name") != -1) {
					cloumnName = ComUtil.getColumn(CmjtSubjectInfoDTO.class,
							sidx.substring(0, sidx.lastIndexOf("Name")));
				}
				if (cloumnName != null) {
					orderBy = " " + cloumnName + " " + sord;
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		CmjtSubjectInfoDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<CmjtSubjectInfoDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<CmjtSubjectInfoDTO>() {
			});
		}else{
			param = new CmjtSubjectInfoDTO();
		}
		//合同ID
		String pid = ServletRequestUtils.getStringParameter(request, "pid", "");
		param.setZbid(pid);
		queryReqBean.setSearchParams(param);
		param.setOrgIdentity(SessionHelper.getCurrentOrgIdentity(request));
		try {
			result = cmjtSubjectInfoService.searchCmjtSubjectInfoByPage(queryReqBean,orderBy,keyWord);
		} catch (Exception ex) {
			return map;
		}
		map.put("records", result.getPageParameter().getTotalCount());
		map.put("page", result.getPageParameter().getPage());
		map.put("total", result.getPageParameter().getTotalPage());
		map.put("rows", result.getResult());
		LOGGER.info("成功获取CmjtSubjectInfoDTO分页数据");
		return map;
	}

	/**
	* 根据传入的的类型跳转到对应页面
	* @param type，包括三个值，分别是Add:新建；Edit：编辑；Detail：详情
	* @param id 数据的id
	* @return ModelAndView
	* @throws Exception
	*/
	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		//编辑窗口或者详细页窗口
		if (!"Add".equals(type)) {
			CmjtSubjectInfoDTO dto = cmjtSubjectInfoService.get(id);
			mav.addObject("cmjtSubjectInfoDTO", dto);
		}
		mav.setViewName("avicit/cmjt/cmjtsubjectinfo/CmjtSubjectInfo" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * @param id 主键id
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveCmjtSubjectInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			CmjtSubjectInfoDTO cmjtSubjectInfoDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<CmjtSubjectInfoDTO>() {
					});
			if (StringUtils.isEmpty(cmjtSubjectInfoDTO.getId())) {
				//新增
				cmjtSubjectInfoDTO.setOrgIdentity(SessionHelper.getCurrentOrgIdentity(request));
				String id = cmjtSubjectInfoService.insert(cmjtSubjectInfoDTO);
				mav.addObject("id", id);
			} else {
				//修改
				cmjtSubjectInfoService.updateSensitive(cmjtSubjectInfoDTO);
				mav.addObject("id", cmjtSubjectInfoDTO.getId());
			}
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;

	}

	/**
	 * 按照id批量删除数据
	 * @param ids id数组
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteCmjtSubjectInfo(@RequestBody String[] ids) {
		ModelAndView mav = new ModelAndView();
		try {
			cmjtSubjectInfoService.deleteByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
}

