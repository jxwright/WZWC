package avicit.cmjt.cmjtcontract.service;

import avicit.cmjt.cmjtcontract.dao.CmjtContractDAO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractResposeDTO;
import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.cmjt.cmjtpayreceinfo.service.CmjtPayReceInfoService;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.cmjt.cmjtsigninfo.service.CmjtSignInfoService;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.cmjt.cmjtsubjectinfo.service.CmjtSubjectInfoService;
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
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 11:31
* @类说明：合同主表Service
* @修改记录：
*/
@Service
public class CmjtContractService {

	private static final Logger logger = LoggerFactory.getLogger(CmjtContractService.class);

	@Autowired
	private CmjtContractDAO cmjtContractDAO;

	@Autowired
	private CmjtSignInfoService cmjtSignInfoService;

	@Autowired
	private CmjtSubjectInfoService cmjtSubjectInfoService;

	@Autowired
	private CmjtPayReceInfoService cmjtPayReceInfoService;

	/**
	 * 查询（分页）
	 * @param queryReqBean 分页
	 * @param orderBy 排序语句
	 * @param keyWord 快速查询条件
	 * @return QueryRespBean<CmjtContractDTO>
	 * @throws Exception
	 */
	public QueryRespBean<CmjtContractDTO> searchCmjtContractByPage(QueryReqBean<CmjtContractDTO> queryReqBean, String orderBy, String keyWord) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			CmjtContractDTO searchParams = queryReqBean.getSearchParams();
			Page<CmjtContractDTO> dataList = cmjtContractDAO.searchCmjtContractByPage(searchParams, orderBy, keyWord);
			QueryRespBean<CmjtContractDTO> queryRespBean = new QueryRespBean<CmjtContractDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			logger.error("searchCmjtContractByPage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 */
	@Transactional(readOnly = true)
	public QueryRespBean<CmjtContractDTO> findListByPage(QueryReqBean<CmjtContractDTO> queryReqBean) {
		QueryRespBean<CmjtContractDTO> queryRespBean = new QueryRespBean();
		PageHelper.startPage(queryReqBean.getPageParameter());
		Page<CmjtContractDTO> page= cmjtContractDAO.findListByPage(queryReqBean.getSearchParams());
		queryRespBean.setResult(page);
		return queryRespBean;
	}

	/**
	* 按条件查询
	*
	* @param queryReqBean 查询条件
	* @return List<CmjtContractDTO>
	*/
	@Transactional(readOnly = true)
	public List<CmjtContractDTO> findList(QueryReqBean<CmjtContractDTO> queryReqBean) {
	    CmjtContractDTO searchParams = queryReqBean.getSearchParams();
        List<CmjtContractDTO> result = cmjtContractDAO.findList(searchParams);
        return result;
	}

	/**
	* 通过主键查询单条记录
	*
	* @param id 主键id
	* @return CmjtContractDTO
	*/
	@Transactional(readOnly = true)
	public CmjtContractDTO get(String id) {
		CmjtContractDTO cmjtContractDTO = cmjtContractDAO.get(id);
		//记录日志
		return cmjtContractDTO;
	}

	/**
	* 新增对象
	*
	* @param cmjtContractDTO 保存对象
	* @return String
	*/
	@Transactional
	public String insert(CmjtContractDTO cmjtContractDTO) {
		try {
			String id = ComUtil.getId();
			cmjtContractDTO.setId(id);
			PojoUtil.setSysProperties(cmjtContractDTO, PlatformConstant.OpType.insert);
			cmjtContractDAO.insert(cmjtContractDTO);
			//记录日志
			if (cmjtContractDTO != null) {
				SysLogUtil.log4Insert(cmjtContractDTO);
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insertCmjtContract出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	* 批量新增对象
	*
	* @param dtoList 保存对象集合
	* @return int
	*/
	@Transactional
	public int insertBatch(List<CmjtContractDTO> dtoList) {
		return cmjtContractDAO.insertBatch(dtoList);
	}

	/**
	* 修改对象全部字段
	*
	* @param cmjtContractDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateAll(CmjtContractDTO cmjtContractDTO) throws Exception {
	    if (cmjtContractDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtContractDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtContractDAO.updateAll(cmjtContractDTO);
	}

	/**
	* 修改对象部分字段
	*
	* @param cmjtContractDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateSensitive(CmjtContractDTO cmjtContractDTO) throws Exception {
        if (cmjtContractDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtContractDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtContractDAO.updateSensitive(cmjtContractDTO);
	}

	/**
	* 批量更新对象
	*
	* @param dtoList 修改对象集合
	* @return int
	*/
	@Transactional
	public int updateBatch(List<CmjtContractDTO> dtoList) {
		return cmjtContractDAO.updateBatch(dtoList);
	}

	/**
	* 按主键单条删除
	*
	* @param id 主键id
	* @return int
	*/
	@Transactional
	public int delete(String id) {
        int count = cmjtContractDAO.delete(id);
		return count;
	}

	/**
	 * 批量删除数据
	 *
	 * @param ids id的数组
	 * @return int
	 */
	@Transactional
	public int deleteByIds(String[] ids) {
		int count = cmjtContractDAO.deleteByIds(ids);
        return count;
	}

	/**
	 * 定时任务
	 * 调用集团合同台账查询接口,将集团合同数据放到当前表中
	 */
	public void postCmjtContract() {
		try {
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = dateFormat.format(date);
			// 获取集团数据
			Map<String, String> param = new HashMap<>();
			param.put("creationDateBegin", "2000-01-01");
			param.put("creationDateEnd", "2025-12-31");
			param.put("orgCode", "131320");
			JSONObject jsonObject = ContractService.queryContracts(param);

			if (jsonObject == null || !"200".equals(jsonObject.getString("retCode"))) {
				return;
			}

			List<CmjtContractDTO> contracts = JSON.parseArray(jsonObject.getJSONArray("responseBody").toJSONString(),CmjtContractDTO.class);
			if(contracts == null || contracts.isEmpty()){
				return;
			}

			// 处理合同数据
			processContracts(contracts);

			// 调用新增数据接口将数据存到当前数据库中
			this.insertBatch(contracts);
		} catch (Exception e) {
			logger.error("处理合同数据时发生异常", e);
			throw new RuntimeException("处理合同数据失败", e);
		}
	}

	/**
	 * 新增合同签约信息和合同收付款计划
	 * @param contracts
	 */
	private void processContracts(List<CmjtContractDTO> contracts) {
		contracts.forEach(contract -> {
			// 新增合同签约信息
			List<CmjtSignInfoDTO> signInfoList = contract.getCmSignInfoList();
			if (CollectionUtils.isNotEmpty(signInfoList)) {
				cmjtSignInfoService.insertBatch(signInfoList);
			}

			//新增合同标的物信息
			List<CmjtSubjectInfoDTO> subjectInfoList = contract.getCmSubject064BdList();
			if (CollectionUtils.isNotEmpty(subjectInfoList)) {
				cmjtSubjectInfoService.insertBatch(subjectInfoList);
			}

			// 新增合同收付款计划
			List<CmjtPayReceInfoDTO> payReceInfoList = contract.getCmSubject102BdList();
			if (CollectionUtils.isNotEmpty(payReceInfoList)) {
				cmjtPayReceInfoService.insertBatch(payReceInfoList);
			}

		});
	}

	public List<CmjtContractDTO>findContractsToSync(){
		return cmjtContractDAO.findContractsToSync();
	}

	public int updateSyncStatus(String contractId,String syncStatus,String msg){
		return cmjtContractDAO.updateSyncStatus(contractId,syncStatus,msg);
	}

	public List<CmjtContractResposeDTO>selectCmjtContractByDate(String startDate, String endDate){
		return cmjtContractDAO.selectCmjtContractByDate(startDate,endDate);
	}

}

