package avicit.cmjt.cmjtcontract.controller;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtcontract.service.CmjtContractService;
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
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-24 16:06
 * @类说明：
 * @修改记录： 
 */
@Controller
@Scope("prototype")
@RequestMapping("avicit/cmjt/cmjtContract/cmjtContractController")
public class CmjtContractController implements LoaderConstant {
	private static final Logger LOGGER = LoggerFactory.getLogger(CmjtContractController.class);

	@Autowired
	private SysApplicationAPI sysApplicationAPI;
	@Autowired
	private CmjtContractService cmjtContractService;

	/**
	 * 跳转到列表页面
	 * @param request 请求
	 * @param reponse 响应
	 * @return ModelAndView
	 */
	@RequestMapping(value = "toCmjtContractManage")
	public ModelAndView toCmjtContractManage(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/cmjt/cmjtcontract/CmjtContractManage");
		mav.addObject("url", "avicit/cmjt/cmjtContract/cmjtContractController/operation/");
		mav.addObject("cmjtPayReceInfoUrl", "avicit/cmjt/cmjtPayReceInfo/cmjtPayReceInfoController/operation/");
		mav.addObject("cmjtSignInfoUrl", "avicit/cmjt/cmjtSignInfo/cmjtSignInfoController/operation/");
		mav.addObject("cmjtSubjectInfoUrl", "avicit/cmjt/cmjtSubjectInfo/cmjtSubjectInfoController/operation/");
		return mav;
	}

	/**
	 * 列表页面分页查询
	 * @param pageParameter 查询条件
	 * @param request 请求
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/operation/getCmjtContractsByPage")
	@ResponseBody
	public Map<String, Object> togetCmjtContractByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<CmjtContractDTO> queryReqBean = new QueryReqBean<CmjtContractDTO>();
		queryReqBean.setPageParameter(pageParameter);
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
			cloumnName = ComUtil.getColumn(CmjtContractDTO.class, sidx);
			if (cloumnName != null) {
				//这里先进行判断是否有对应的数据库字段
				orderBy = " " + cloumnName + " " + sord;
			} else {
				//判断是否为特殊控件导致字段无法匹配
				if (sidx.indexOf("Alias") != -1) {
					cloumnName = ComUtil.getColumn(CmjtContractDTO.class,
							sidx.substring(0, sidx.lastIndexOf("Alias")));
				} else if (sidx.indexOf("Name") != -1) {
					cloumnName = ComUtil.getColumn(CmjtContractDTO.class,
							sidx.substring(0, sidx.lastIndexOf("Name")));
				}
				if (cloumnName != null) {
					orderBy = " " + cloumnName + " " + sord;
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		CmjtContractDTO param = new CmjtContractDTO();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<CmjtContractDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<CmjtContractDTO>() {
			});
			queryReqBean.setSearchParams(param);
		}
		param.setOrgIdentity(SessionHelper.getCurrentOrgIdentity(request));
		try {
			result = cmjtContractService.searchCmjtContractByPage(queryReqBean,orderBy,keyWord);
		} catch (Exception ex) {
			return map;
		}
		map.put("records", result.getPageParameter().getTotalCount());
		map.put("page", result.getPageParameter().getPage());
		map.put("total", result.getPageParameter().getTotalPage());
		map.put("rows", result.getResult());
		LOGGER.info("成功获取CmjtContractDTO分页数据");
		return map;
	}

	/**
	* 根据传入的的类型跳转到对应页面
	* @param type，包括三个值，分别是Add:新建；Edit：编辑；Detail：详情
	* @param id 数据的id
	* @param request 请求
	* @return ModelAndView
	* @throws Exception
	*/
	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"Add".equals(type)) {
			//编辑窗口或者详细页窗口
			CmjtContractDTO dto = cmjtContractService.get(id);
			mav.addObject("cmjtContractDTO", dto);
		}
		mav.setViewName("avicit/cmjt/cmjtcontract/CmjtContract" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveCmjtContract(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			CmjtContractDTO cmjtContractDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<CmjtContractDTO>() {
					});
			if (StringUtils.isEmpty(cmjtContractDTO.getId())) {
				//新增
				cmjtContractDTO.setOrgIdentity(SessionHelper.getCurrentOrgIdentity(request));
				String id = cmjtContractService.insert(cmjtContractDTO);
				mav.addObject("id", id);
			} else {
				//修改
				cmjtContractService.updateSensitive(cmjtContractDTO);
				mav.addObject("id", cmjtContractDTO.getId());
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
	 * @param request 请求
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteCmjtContract(@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			cmjtContractService.deleteByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
}

