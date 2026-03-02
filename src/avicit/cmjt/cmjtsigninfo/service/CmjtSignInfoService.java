package avicit.cmjt.cmjtsigninfo.service;

import avicit.cmjt.cmjtsigninfo.dao.CmjtSignInfoDAO;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.platform6.commons.utils.*;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.core.rest.msg.QueryReqBean;
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
* @类说明：合同签约信息表Service
* @修改记录：
*/
@Service
public class CmjtSignInfoService {

	private static final Logger logger = LoggerFactory.getLogger(CmjtSignInfoService.class);

	@Autowired
	private CmjtSignInfoDAO cmjtSignInfoDAO;

	/**
	 * 查询（分页）
	 * @param queryReqBean 分页
	 * @param orderBy 排序语句
	 * @param keyWord 快速查询条件
	 * @return QueryRespBean<DynCmjtSignInfoDTO>
	 * @throws Exception
	 */
	public QueryRespBean<CmjtSignInfoDTO> searchCmjtSignInfoByPage(QueryReqBean<CmjtSignInfoDTO> queryReqBean, String orderBy, String keyWord) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			CmjtSignInfoDTO searchParams = queryReqBean.getSearchParams();
			//表单数据查询
			Page<CmjtSignInfoDTO> dataList = cmjtSignInfoDAO.searchCmjtSignInfoByPage(searchParams, orderBy, keyWord);
			QueryRespBean<CmjtSignInfoDTO> queryRespBean = new QueryRespBean<CmjtSignInfoDTO>();
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
	public QueryRespBean<CmjtSignInfoDTO> findListByPage(QueryReqBean<CmjtSignInfoDTO> queryReqBean) {
		QueryRespBean<CmjtSignInfoDTO> queryRespBean = new QueryRespBean();
		PageHelper.startPage(queryReqBean.getPageParameter());
		Page<CmjtSignInfoDTO> page= cmjtSignInfoDAO.findListByPage(queryReqBean.getSearchParams());
		queryRespBean.setResult(page);
		return queryRespBean;
	}

	/**
	* 按条件查询
	*
	* @param queryReqBean 查询条件
	* @return List<CmjtSignInfoDTO>
	*/
	@Transactional(readOnly = true)
	public List<CmjtSignInfoDTO> findList(QueryReqBean<CmjtSignInfoDTO> queryReqBean) {
	    CmjtSignInfoDTO searchParams = queryReqBean.getSearchParams();

        List<CmjtSignInfoDTO> result = cmjtSignInfoDAO.findList(searchParams);
        return result;
	}

	/**
	* 通过主键查询单条记录
	*
	* @param id 主键id
	* @return CmjtSignInfoDTO
	*/
	@Transactional(readOnly = true)
	public CmjtSignInfoDTO get(String id) {
		CmjtSignInfoDTO cmjtSignInfoDTO = cmjtSignInfoDAO.get(id);
		return cmjtSignInfoDTO;
	}

	/**
	 * 新增对象
	 *
	 * @param cmjtSignInfoDTO 保存对象
	 * @return String
	 */
	@Transactional
	public String insert(CmjtSignInfoDTO cmjtSignInfoDTO) {
		try {
			String id = ComUtil.getId();
			cmjtSignInfoDTO.setId(id);
			PojoUtil.setSysProperties(cmjtSignInfoDTO, PlatformConstant.OpType.insert);
			cmjtSignInfoDAO.insert(cmjtSignInfoDTO);
			//记录日志
			if (cmjtSignInfoDTO != null) {
				SysLogUtil.log4Insert(cmjtSignInfoDTO);
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insertCmjtSignInfo出错：", e);
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
	public int insertBatch(List<CmjtSignInfoDTO> dtoList) {
		return cmjtSignInfoDAO.insertBatch(dtoList);
	}

	/**
	* 修改对象全部字段
	*
	* @param cmjtSignInfoDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateAll(CmjtSignInfoDTO cmjtSignInfoDTO) throws Exception {
	    if (cmjtSignInfoDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtSignInfoDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtSignInfoDAO.updateAll(cmjtSignInfoDTO);
	}

	/**
	* 修改对象部分字段
	*
	* @param cmjtSignInfoDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateSensitive(CmjtSignInfoDTO cmjtSignInfoDTO) throws Exception {
        if (cmjtSignInfoDTO == null) {
            throw new Exception("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(cmjtSignInfoDTO.getId())) {
            throw new Exception("修改对象的id不能为空！");
        }
        return cmjtSignInfoDAO.updateSensitive(cmjtSignInfoDTO);
	}

	/**
	* 批量更新对象
	*
	* @param dtoList 修改对象集合
	* @return int
	*/
	@Transactional
	public int updateBatch(List<CmjtSignInfoDTO> dtoList) {
		return cmjtSignInfoDAO.updateBatch(dtoList);
	}

	/**
	* 按主键单条删除
	*
	* @param id 主键id
	* @return int
	*/
	@Transactional
	public int delete(String id) {
        int count = cmjtSignInfoDAO.delete(id);
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
		int count = cmjtSignInfoDAO.deleteByIds(ids);
        return count;
	}

	/**
	 * 按合同id查询签约信息
	 *
	 * @param contractIds
	 * @return
	 */
	public List<CmjtSignInfoDTO> selectByContractIds(List<String> contractIds){
		return cmjtSignInfoDAO.selectByContractIds(contractIds);
	}

}

