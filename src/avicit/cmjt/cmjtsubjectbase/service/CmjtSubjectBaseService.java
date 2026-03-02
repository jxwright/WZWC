package avicit.cmjt.cmjtsubjectbase.service;

import avicit.cmjt.cmjtsubjectbase.dao.CmjtSubjectBaseDAO;
import avicit.cmjt.cmjtsubjectbase.dto.CmjtSubjectBaseDTO;
import avicit.cmjt.utils.ContractService;
import avicit.platform6.commons.utils.*;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 09:57
* @类说明：标的物库表Service
* @修改记录：
*/
@Service
public class CmjtSubjectBaseService {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSubjectBaseService.class);

	@Autowired
	private CmjtSubjectBaseDAO cmjtSubjectBaseDAO;

	/**
	 * 批量新增对象
	 * @param dtoList 保存对象集合
	 * @return int
	 */
	@Transactional
	public int insertBatch(List<CmjtSubjectBaseDTO> dtoList) throws Exception {
		if (CollectionUtils.isEmpty(dtoList)) {
			logger.warn("未获取到需要新增的标的物库");
			throw new Exception("未获取到需要新增的标的物库");
		}

		try {
			Map<String,Object> params = new HashMap<>();
			// 1. 初始化DTO列表
			for(CmjtSubjectBaseDTO dto : dtoList){
				//新增数据之前判断是否在集团中已存在
				params.put("businessId",dto.getBusinessId());
				JSONObject jsonObject = ContractService.querySubjects(params);
				if (jsonObject != null && "200".equals(jsonObject.getString("retCode"))) {
					List<CmjtSubjectBaseDTO> contracts = JSON.parseArray(jsonObject.getJSONArray("responseBody").toJSONString(),CmjtSubjectBaseDTO.class);
					if(contracts != null && !contracts.isEmpty()){
						throw new Exception(String.format("业务ID为%s的标的物已存在，不能重复添加",dto.getBusinessId()));
					}
				}

				//不存在则初始化DTO
				dto.setId(ComUtil.getId());
				dto.setOperationType("insert");
				PojoUtil.setSysProperties(dto, PlatformConstant.OpType.insert);
				dto.setIsTrans("0"); // 默认设置为未推送
			}

			// 2. 批量插入本地数据库
			int count = cmjtSubjectBaseDAO.insertCmjtSubjectBaseList(dtoList);

			// 3. 推送数据到集团并更新状态
			processGroupPush(dtoList);

			// 4. 批量更新推送状态
			cmjtSubjectBaseDAO.updateCmjtSubjectBaseList(dtoList);
			return count;
		} catch (IllegalArgumentException e) {
			logger.warn("数据验证失败:{}",e.getMessage());
			throw e;
		}catch (Exception e) {
			logger.error("新增标的物库数据失败", e);
			throw new Exception("新增标的物库数据失败"+e.getMessage());
		}
	}

	/**
	 * 推送新增数据到集团
	 * @param dtoList
	 */
	private void processGroupPush(List<CmjtSubjectBaseDTO> dtoList) {
		dtoList.forEach(dto -> {
			try {
				JSONObject jsonObject = ContractService.addSubject(this.dtoConvertToParams(dto,"insert"));
				if (jsonObject != null && "200".equals(jsonObject.getString("retCode"))) {
					String id = jsonObject.getString("responseBody");
					dto.setJtId(id);
					dto.setIsTrans("1"); // 推送成功
					dto.setErrorInfo(""); //清空错误信息
				}
			} catch (Exception e) {
				dto.setErrorInfo(e.getMessage());
				logger.error("调用集团标的物库增加接口失败, ID: {}", dto.getId(), e);
			}
		});
	}

	/**
	 * 批量更新对象
	 * @param dtoList 修改对象集合
	 * @return int
	 */
	@Transactional
	public int updateBatch(List<CmjtSubjectBaseDTO> dtoList) throws Exception {
		int count = dtoList.size();
		if (CollectionUtils.isEmpty(dtoList)) {
			logger.warn("未获取到需要更新的标的");
			throw new Exception("未获取到需要更新的标的");
		}

		try {
			// 1. 预处理数据
			dtoList.forEach(dto -> {
				dto.setOperationType("update");
				PojoUtil.setSysProperties(dto, PlatformConstant.OpType.update);
				//2. 更新本地数据
				cmjtSubjectBaseDAO.updateSensitiveBySourceAndBusinessId(dto);
			});

			// 2. 批量更新本地数据
//			cmjtSubjectBaseDAO.updateCmjtSubjectBaseList(dtoList);

			// 3. 处理远程更新（顺序处理以确保事务性）
			Iterator<CmjtSubjectBaseDTO> iterator = dtoList.iterator();
			while (iterator.hasNext()) {
				CmjtSubjectBaseDTO dto = iterator.next();
				try {
					JSONObject jsonObject = ContractService.updateSubject(dtoConvertToParams(dto,"update"));
					if (jsonObject != null && "200".equals(jsonObject.getString("retCode"))) {
						iterator.remove(); // 成功则移除，不再处理
					} else {
						dto.setIsTrans("0");
					}
				} catch (Exception e) {
					logger.error("更新标的{}远程数据失败", dto.getId(), e);
					dto.setIsTrans("0");
				}
			}

			// 4. 更新失败记录的本地状态
			if (!dtoList.isEmpty()) {
				dtoList.forEach(dto -> {
					cmjtSubjectBaseDAO.updateSensitiveBySourceAndBusinessId(dto);
				});
//				cmjtSubjectBaseDAO.updateCmjtSubjectBaseList(dtoList);
			}
			count = count - dtoList.size();
			return count; // 返回成功数量

		} catch (Exception e) {
			logger.error("批量更新标的失败", e);
			throw new Exception("批量更新标的失败"+e.getMessage()); // 抛出异常让事务回滚
		}
	}


	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return int
	 */
	@Transactional
	public int deleteByIds(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			throw new Exception("未获取到需要删除的数据");
		}

		List<String> successDeletedIds = new ArrayList<>();
		for (String id : ids) {
			try {
				CmjtSubjectBaseDTO dto = cmjtSubjectBaseDAO.findCmjtSubjectBaseById(id);
				if (dto == null) {
					logger.warn("未找到ID为 {} 的数据", id);
					continue;
				}

				JSONObject result = ContractService.deleteSubject(dto.getBusinessId());
				if (result == null || !"200".equals(result.getString("retCode"))) {
					logger.error("删除集团数据失败，ID: {}, 返回结果: {}", id, result);
					continue;
				}

				successDeletedIds.add(id);
			} catch (Exception e) {
				logger.error("处理ID为 {} 的数据时发生异常", id, e);
				throw new Exception("处理ID为 {"+id+"} 的数据时发生异常"+e.getMessage());
			}
		}

		return successDeletedIds.isEmpty() ? 0 : cmjtSubjectBaseDAO.deleteByIds(successDeletedIds);
	}

	/**
	 * 批量删除数据
	 * @param dataSource 数据来源
	 * @param businessIds id的数组
	 * @return int
	 */
	@Transactional
	public int deleteBySourceAndBusinessIds(String dataSource, List<String> businessIds) throws Exception {
		if (businessIds == null || businessIds.size() == 0 || StringUtils.isEmpty(dataSource)) {
			throw new Exception("未获取到需要删除的数据");
		}

		List<String> successDeletedBusinessIds = new ArrayList<>();
		for (String businessId : businessIds) {
			try {
				//判断当前数据的数据在集团中是否存在，不存在则直接删除
				Map<String, Object> params = new HashMap<>();
				params.put("businessId",businessId);
				JSONObject jsonObject = ContractService.querySubjects(params);
				if (jsonObject != null && "200".equals(jsonObject.getString("retCode"))) {
					List<CmjtSubjectBaseDTO> contracts = JSON.parseArray(jsonObject.getJSONArray("responseBody").toJSONString(),CmjtSubjectBaseDTO.class);
					if(contracts == null || contracts.isEmpty()){
						//删除本地数据
						cmjtSubjectBaseDAO.deleteCmjtSubjectBaseBySourceAndBusinessId(dataSource, businessId);
						successDeletedBusinessIds.add(businessId);
						continue;
					}
				}
				//在集团中存在时，则调用集团接口先删除集团数据
				JSONObject result = ContractService.deleteSubject(businessId);
				if (result == null || !"200".equals(result.getString("retCode"))) {
					logger.error("删除集团数据失败，BUSINESS_ID: {}, 返回结果: {}", businessId, result);
					continue;
				}
				//删除本地数据
				cmjtSubjectBaseDAO.deleteCmjtSubjectBaseBySourceAndBusinessId(dataSource, businessId);
				successDeletedBusinessIds.add(businessId);
			} catch (Exception e) {
				logger.error("处理ID为 {} 的数据时发生异常", businessId, e);
				throw new Exception("处理BUSINESS_ID为 {"+businessId+"} 的数据时发生异常"+e.getMessage());
			}
		}

		return successDeletedBusinessIds.isEmpty() ? 0 : successDeletedBusinessIds.size();
	}

//	/**
//	 * 按条件分页查询
//	 * @param queryReqBean
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public QueryRespBean<CmjtSubjectBaseDTO> findListByPage(QueryReqBean<CmjtSubjectBaseDTO> queryReqBean) {
//		QueryRespBean<CmjtSubjectBaseDTO> queryRespBean = new QueryRespBean();
//		PageHelper.startPage(queryReqBean.getPageParameter());
//		Page<CmjtSubjectBaseDTO> page= cmjtSubjectBaseDAO.findListByPage(queryReqBean.getSearchParams());
//		queryRespBean.setResult(page);
//		return queryRespBean;
//	}

	/**
	 * 按条件查询
	 *
	 * @param cmjtSubjectBaseDTO 查询条件
	 * @return List<CmjtSubjectInfoDTO>
	 */
	@Transactional(readOnly = true)
	public List<CmjtSubjectBaseDTO> findList(CmjtSubjectBaseDTO cmjtSubjectBaseDTO) throws Exception {
		Date lastUpdateDateBegin = cmjtSubjectBaseDTO.getLastUpdateDateBegin();
		Date lastUpdateDateEnd = cmjtSubjectBaseDTO.getLastUpdateDateEnd();
		if(lastUpdateDateEnd == null || lastUpdateDateBegin == null){
			throw new Exception("lastUpdateDateBegin和lastUpdateDateEnd不能为空");
		}
		List<CmjtSubjectBaseDTO> result = cmjtSubjectBaseDAO.searchCmjtSubjectBase(cmjtSubjectBaseDTO);
		return result;
	}

	/**
	 * 修改对象部分字段
	 * @param cmjtSubjectBaseDTO 修改对象
	 * @return int
	 */
	@Transactional
	public int updateSensitive(CmjtSubjectBaseDTO cmjtSubjectBaseDTO) throws Exception {
		if (cmjtSubjectBaseDTO == null) {
			throw new Exception("修改对象不能为空！");
		}
		if (StringUtils.isEmpty(cmjtSubjectBaseDTO.getId())) {
			throw new Exception("修改对象的id不能为空！");
		}
//		return cmjtSubjectBaseDAO.updateSensitive(cmjtSubjectBaseDTO);
		return cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(cmjtSubjectBaseDTO);
	}

	/**
	 * 定时任务
	 * 将未推送到集团的标的信息推送到集团中
	 */
	@Transactional
	public void postCmjtSubjectBase() {
		//获取本地未推送到集团的数据
		CmjtSubjectBaseDTO queryDTO = new CmjtSubjectBaseDTO();
		queryDTO.setIsTrans("0");
//		List<CmjtSubjectBaseDTO> unPushedList = cmjtSubjectBaseDAO.findList(queryDTO);
		List<CmjtSubjectBaseDTO> unPushedList = cmjtSubjectBaseDAO.searchCmjtSubjectBase(queryDTO);

		if (CollectionUtils.isEmpty(unPushedList)) {
			logger.info("未获取到未推送到集团的标的数据");
			return;
		}

		// 将未推送到集团的数据进行推送
		unPushedList.parallelStream().forEach(subject -> {
			try {
				Map<String, Object> params = new HashMap<>();
				params.put("businessId",subject.getBusinessId());
				//推送前，判断当前数据是否已在集团中存在
				JSONObject response = ContractService.querySubjects(params);
				//不存在,将当前数据推送到集团
				if (response == null || !"200".equals(response.getString("retCode"))) {
					processUnpushedSubject(subject);
				}else{
					JSONArray responseBody = response.getJSONArray("responseBody");
					if(responseBody == null || responseBody.isEmpty()){
						//不存在则将当前数据推送到集团
						processUnpushedSubject(subject);
					}else{
						//存在，则更新本地数据
						updateSubjectAfterSuccessfulPush(subject, response);
					}
				}
			} catch (Exception e) {
				//记录推送失败原因
				updateSubjectAfterFailurePush(subject, e);
				logger.error("推送失败标的ID: {}", subject.getId(), e);
			}
		});
	}

	/**
	 * 将未推送到集团的标的信息调用相关接口进行推送
	 * @param subject
	 */
	private void processUnpushedSubject(CmjtSubjectBaseDTO subject) {
		String operationType = subject.getOperationType();
		if (StringUtils.isEmpty(operationType)) {
			logger.warn("标的ID: {} 的操作类型为空", subject.getId());
			return;
		}

		JSONObject response = null;
		try {
			//判断数据未推送成功的原因，调用相关的接口
			switch (operationType) {
				case "insert":
					response = ContractService.addSubject(dtoConvertToParams(subject,"insert"));
					break;
				case "update":
					response = ContractService.updateSubject(dtoConvertToParams(subject,"update"));
					break;
				default:
					logger.warn("未知的操作类型：{} ，标的ID: {}", operationType, subject.getId());
					return;
			}

			if (response == null || !"200".equals(response.getString("retCode"))) {
				throw new RuntimeException("推送标的失败");
			}
			updateSubjectAfterSuccessfulPush(subject, response);
		} catch (Exception e) {
			logger.error("推送标的ID: {} 失败", subject.getId(), e);
			throw new RuntimeException("推送标的失败", e);
		}
	}

	/**
	 * 推送成功后，更新本地的标的信息
	 * @param subject
	 * @param response
	 */
	private void updateSubjectAfterSuccessfulPush(CmjtSubjectBaseDTO subject, JSONObject response) throws Exception {
		String id = response.getString("responseBody");
		if(StringUtils.isEmpty(id)){
			throw new Exception("获取集团标的信息失败");
		}
		subject.setJtId(id);
		subject.setIsTrans("1");
		PojoUtil.setSysProperties(subject, PlatformConstant.OpType.update);

		try {
			cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(subject);
		} catch (Exception e) {
			logger.error("推送成功后未能更新本地状态: {}", subject.getId(), e);
		}
	}

	/**
	 * 推送失败后，记录失败原因
	 * @param subject
	 * @param e
	 */
	private void updateSubjectAfterFailurePush(CmjtSubjectBaseDTO subject, Exception e) {
		subject.setIsTrans("0");
		subject.setErrorInfo(e.getMessage());
		PojoUtil.setSysProperties(subject, PlatformConstant.OpType.update);
		cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(subject);
	}


	/**
	 * 将dto转换成标的增加，标的更新需要的参数
	 * @param dto
	 */
	private Map<String, Object> dtoConvertToParams(CmjtSubjectBaseDTO dto, String operationType) {
		//新增和更新通用参数
		Map<String, Object> params = new HashMap<>();
		params.put("businessId",dto.getBusinessId());
		params.put("deliverDate",dto.getDeliverDate());
		params.put("increDecreRate",dto.getIncreDecreRate());
		params.put("lastUpdateDate",dto.getLastUpdateDate());
		params.put("planType",dto.getPlanType());
		params.put("specsType",dto.getSpecsType());
		params.put("subjectName",dto.getSubjectName());
		params.put("subjectNum",dto.getSubjectNum());
		params.put("subjectPrice",dto.getSubjectPrice());
		params.put("subjectTotalAmount",dto.getSubjectTotalAmount());
		params.put("taxRate",dto.getTaxRate());
		params.put("unit",dto.getUnit());
		//不同的参数
		switch (operationType) {
			case "insert":
				break;
			case "update":
				params.put("id","1");
				break;
			default:
				logger.warn("未知的操作类型：{} ，标的ID: {}", operationType, dto.getId());
				return params;
		}
		return params;
	}

	/**
	 * 判断是否存在已被合同起草占用的标的数据
	 * @param subjectId
	 * @return
	 */
	private boolean isSubjectUsedInContract(String subjectId) {
		//todo 判断是否存在已被合同起草占用的标的数据
		return false;
	}


	/**
	 * 查询（分页）
	 * @param queryReqBean 分页
	 * @param orderBy 排序语句
	 * @param keyWord 快速查询条件
	 * @return QueryRespBean<CmjtSubjectBaseDTO>
	 * @throws Exception
	 */
	public QueryRespBean<CmjtSubjectBaseDTO> searchCmjtSubjectBaseByPage(QueryReqBean<CmjtSubjectBaseDTO> queryReqBean, String orderBy, String keyWord) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			CmjtSubjectBaseDTO searchParams = queryReqBean.getSearchParams();
			//表单数据查询
			Page<CmjtSubjectBaseDTO> dataList = cmjtSubjectBaseDAO.searchCmjtSubjectBaseByPage(searchParams, orderBy, keyWord);
			QueryRespBean<CmjtSubjectBaseDTO> queryRespBean = new QueryRespBean<CmjtSubjectBaseDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("searchCmjtSubjectBaseByPage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 主键查询
	 * @param id 主键id
	 * @return CmjtSubjectBaseDTO
	 * @throws Exception
	 */
	public CmjtSubjectBaseDTO queryCmjtSubjectBaseByPrimaryKey(String id) throws Exception {
		try {
			CmjtSubjectBaseDTO dto = cmjtSubjectBaseDAO.findCmjtSubjectBaseById(id);
			//记录日志
			if (dto != null) {
				SysLogUtil.log4Query(dto);
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCmjtSubjectBaseByPrimaryKey出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 新增
	 * @param dto 保存对象
	 * @return String
	 * @throws Exception
	 */
	public String insertCmjtSubjectBase(CmjtSubjectBaseDTO dto) throws Exception {
		try {
			//新增数据之前判断是否在集团中已存在
			Map<String,Object> params = new HashMap<>();
			params.put("businessId",dto.getBusinessId());
			JSONObject jsonObject = ContractService.querySubjects(params);
			if (jsonObject != null && "200".equals(jsonObject.getString("retCode"))) {
				List<CmjtSubjectBaseDTO> contracts = JSON.parseArray(jsonObject.getJSONArray("responseBody").toJSONString(),CmjtSubjectBaseDTO.class);
				if(contracts != null && !contracts.isEmpty()){
					throw new IllegalArgumentException(String.format("业务ID为%s的标的物已存在，不能重复添加",dto.getBusinessId()));
				}
			}
			//不存在则初始化DTO
			String id = ComUtil.getId();
			dto.setId(id);
			dto.setOperationType("insert");
			PojoUtil.setSysProperties(dto, PlatformConstant.OpType.insert);
			dto.setIsTrans("0"); // 默认设置为未推送

			// 2. 批量插入本地数据库
			cmjtSubjectBaseDAO.insertCmjtSubjectBase(dto);

			// 3. 推送数据到集团并更新状态
			List<CmjtSubjectBaseDTO> list = new ArrayList<>();
			list.add(dto);
			processGroupPush(list);

			// 4. 批量更新推送状态
			cmjtSubjectBaseDAO.updateCmjtSubjectBaseSensitive(list.get(0));

			//记录日志
			if (list.get(0) != null) {
				SysLogUtil.log4Insert(list.get(0));
			}
			return id;
		} catch (IllegalArgumentException e) {
			logger.warn("数据验证失败:{}",e.getMessage());
			throw e;
		}catch (Exception e) {
			logger.error("新增标的物库数据失败", e);
			throw new RuntimeException("新增标的物库数据失败", e);
		}
	}

}

