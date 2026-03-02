package avicit.cmjt.cmjtsubjectinfo.service;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtsubjectinfo.dao.CmjtSubjectInfoDAO;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.commons.utils.*;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:44
* @类说明：合同标的物信息表Service
* @修改记录：
*/
@Service
public class CmjtSubjectInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSubjectInfoService.class);

	@Autowired
	private CmjtSubjectInfoDAO cmjtSubjectInfoDAO;

	/**
	 * 查询（分页）
	 * @param queryReqBean 分页
	 * @param orderBy 排序语句
	 * @param keyWord 快速查询条件
	 * @return QueryRespBean<CmjtSignInfoDTO>
	 * @throws Exception
	 */
	public QueryRespBean<CmjtSubjectInfoDTO> searchCmjtSubjectInfoByPage(QueryReqBean<CmjtSubjectInfoDTO> queryReqBean, String orderBy, String keyWord) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			CmjtSubjectInfoDTO searchParams = queryReqBean.getSearchParams();
			//表单数据查询
			Page<CmjtSubjectInfoDTO> dataList = cmjtSubjectInfoDAO.searchCmjtSubjectInfoByPage(searchParams, orderBy, keyWord);
			QueryRespBean<CmjtSubjectInfoDTO> queryRespBean = new QueryRespBean<CmjtSubjectInfoDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("searchCmjtSignInfoByPage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 */
	@Transactional(readOnly = true)
	public QueryRespBean<CmjtSubjectInfoDTO> findListByPage(QueryReqBean<CmjtSubjectInfoDTO> queryReqBean) {
		QueryRespBean<CmjtSubjectInfoDTO> queryRespBean = new QueryRespBean();
		PageHelper.startPage(queryReqBean.getPageParameter());
		Page<CmjtSubjectInfoDTO> page= cmjtSubjectInfoDAO.findListByPage(queryReqBean.getSearchParams());
		queryRespBean.setResult(page);
		return queryRespBean;
	}

	/**
	* 按条件查询
	*
	* @param queryReqBean 查询条件
	* @return List<CmjtSubjectInfoDTO>
	*/
	@Transactional(readOnly = true)
	public List<CmjtSubjectInfoDTO> findList(QueryReqBean<CmjtSubjectInfoDTO> queryReqBean) {
	    CmjtSubjectInfoDTO searchParams = queryReqBean.getSearchParams();

        List<CmjtSubjectInfoDTO> result = cmjtSubjectInfoDAO.findList(searchParams);
        return result;
	}

	/**
	* 通过主键查询单条记录
	*
	* @param id 主键id
	* @return CmjtSubjectInfoDTO
	*/
	@Transactional(readOnly = true)
	public CmjtSubjectInfoDTO get(String id) {
		CmjtSubjectInfoDTO cmjtSubjectInfoDTO = cmjtSubjectInfoDAO.get(id);
		return cmjtSubjectInfoDTO;
	}

	/**
	 * 新增对象
	 *
	 * @param cmjtSubjectInfoDTO 保存对象
	 * @return String
	 */
	@Transactional
	public String insert(CmjtSubjectInfoDTO cmjtSubjectInfoDTO) {
		try {
			String id = ComUtil.getId();
			cmjtSubjectInfoDTO.setId(id);
			PojoUtil.setSysProperties(cmjtSubjectInfoDTO, PlatformConstant.OpType.insert);
			cmjtSubjectInfoDAO.insert(cmjtSubjectInfoDTO);
			//记录日志
			if (cmjtSubjectInfoDTO != null) {
				SysLogUtil.log4Insert(cmjtSubjectInfoDTO);
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insertCmjtSubjectBase出错：", e);
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
	public int insertBatch(List<CmjtSubjectInfoDTO> dtoList) {
		return cmjtSubjectInfoDAO.insertBatch(dtoList);
	}

	/**
	* 修改对象全部字段
	*
	* @param cmjtSubjectInfoDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateAll(CmjtSubjectInfoDTO cmjtSubjectInfoDTO) throws Exception {
	    if (cmjtSubjectInfoDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtSubjectInfoDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtSubjectInfoDAO.updateAll(cmjtSubjectInfoDTO);
	}

	/**
	* 修改对象部分字段
	*
	* @param cmjtSubjectInfoDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateSensitive(CmjtSubjectInfoDTO cmjtSubjectInfoDTO) throws Exception {
        if (cmjtSubjectInfoDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtSubjectInfoDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtSubjectInfoDAO.updateSensitive(cmjtSubjectInfoDTO);
	}

	/**
	* 批量更新对象
	*
	* @param dtoList 修改对象集合
	* @return int
	*/
	@Transactional
	public int updateBatch(List<CmjtSubjectInfoDTO> dtoList) {
		return cmjtSubjectInfoDAO.updateBatch(dtoList);
	}

	/**
	* 按主键单条删除
	*
	* @param id 主键id
	* @return int
	*/
	@Transactional
	public int delete(String id) {
        int count = cmjtSubjectInfoDAO.delete(id);
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
		int count = cmjtSubjectInfoDAO.deleteByIds(ids);
        return count;
	}


	public List<CmjtSubjectInfoDTO> selectByContractIds( List<String> contractId){
		return cmjtSubjectInfoDAO.selectByContractIds(contractId);
	}


}

