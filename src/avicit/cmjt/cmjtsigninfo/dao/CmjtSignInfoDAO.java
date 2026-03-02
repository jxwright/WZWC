package avicit.cmjt.cmjtsigninfo.dao;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtsigninfo.dto.CmjtSignInfoDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:44
* @类说明：合同签约信息表Dao
* @修改记录：
*/
@MyBatisRepository
public interface CmjtSignInfoDAO {

    /**
     * 分页查询
     * @param cmjtSignInfoDTO 查询对象
     * @param orderBy 排序条件
     * @param keyWord 关键字
     * @return Page<DynCmjtSignInfoDTO>
     */
    Page<CmjtSignInfoDTO> searchCmjtSignInfoByPage(@Param("bean") CmjtSignInfoDTO cmjtSignInfoDTO, @Param("pOrderBy") String orderBy, @Param("keyWord") String keyWord);

    /**
     * 分页查询合同收付款计划列表
     * @param searchParams
     * @return
     */
    Page<CmjtSignInfoDTO> findListByPage(@Param("bean") CmjtSignInfoDTO searchParams);
    
    /**
     * 分页查询合同收付款计划列表
     * @param cmjtSignInfoDTO 查询参数
     * @return 合同列表
     */
    List<CmjtSignInfoDTO> findList(@Param("bean") CmjtSignInfoDTO cmjtSignInfoDTO);

    /**
     * 根据ID列表批量查询合同
     * @param idList ID列表
     * @return 合同列表
     */
    List<CmjtSignInfoDTO> findListByIds(@Param("idList") List<String> idList);

    /**
     * 根据ID查询合同详情
     * @param id 合同ID
     * @return 合同详情
     */
    CmjtSignInfoDTO get(@Param("id") String id);

    /**
     * 新增合同
     * @param CmjtSignInfoDTO 合同对象
     * @return 影响行数
     */
    int insert(CmjtSignInfoDTO CmjtSignInfoDTO);

    /**
     * 批量新增合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int insertBatch(@Param("entityList") List<CmjtSignInfoDTO> entityList);

    /**
     * 选择性更新合同(只更新非空字段)
     * @param cmjtSignInfoDTO 合同对象
     * @return 影响行数
     */
    int updateSensitive(CmjtSignInfoDTO cmjtSignInfoDTO);

    /**
     * 全量更新合同(更新所有字段)
     * @param cmjtSignInfoDTO 合同对象
     * @return 影响行数
     */
    int updateAll(CmjtSignInfoDTO cmjtSignInfoDTO);

    /**
     * 批量更新合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int updateBatch(@Param("entityList") List<CmjtSignInfoDTO> entityList);

    /**
     * 根据ID删除合同
     * @param id 合同ID
     * @return 影响行数
     */
    int delete(@Param("id") String id);

    /**
     * 批量删除合同
     * @param ids ID列表
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") String[] ids);

    int updateMerge(CmjtSignInfoDTO cmjtSignInfoDTO);

    /**
     * 根据合同ID查询签约信息
     * @param idList 合同ID
     * @return 合同列表
     */
    List<CmjtSignInfoDTO> selectByContractIds(@Param("idList") List<String> idList);

}