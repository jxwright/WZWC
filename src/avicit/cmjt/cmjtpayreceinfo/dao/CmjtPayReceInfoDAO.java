package avicit.cmjt.cmjtpayreceinfo.dao;

import avicit.cmjt.cmjtpayreceinfo.dto.CmjtPayReceInfoDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-16 10:47
* @类说明：合同收付款计划信息表Dao
* @修改记录：
*/
@MyBatisRepository
public interface CmjtPayReceInfoDAO {

    /**
     * 分页查询
     * @param cmjtPayReceInfoDTO 查询对象
     * @param orderBy 排序条件
     * @param keyWord 关键字
     * @return Page<DynPayReceInfoDTO>
     */
    Page<CmjtPayReceInfoDTO> searchCmjtPayReceInfoByPage(@Param("bean") CmjtPayReceInfoDTO cmjtPayReceInfoDTO, @Param("pOrderBy") String orderBy, @Param("keyWord") String keyWord);


    /**
     * 分页查询合同列表
     * @param searchParams
     * @return
     */
    Page<CmjtPayReceInfoDTO> findListByPage(@Param("bean") CmjtPayReceInfoDTO searchParams);

    /**
     * 分页查询合同收付款计划列表
     * @param cmjtPayReceInfoDTO 查询参数
     * @return 合同列表
     */
    List<CmjtPayReceInfoDTO> findList(@Param("bean") CmjtPayReceInfoDTO cmjtPayReceInfoDTO);

    /**
     * 根据ID列表批量查询合同
     * @param idList ID列表
     * @return 合同列表
     */
    List<CmjtPayReceInfoDTO> findListByIds(@Param("idList") List<String> idList);

    /**
     * 根据ID查询合同详情
     * @param id 合同ID
     * @return 合同详情
     */
    CmjtPayReceInfoDTO get(@Param("id") String id);

    /**
     * 新增合同
     * @param cmjtPayReceInfoDTO 合同对象
     * @return 影响行数
     */
    int insert(CmjtPayReceInfoDTO cmjtPayReceInfoDTO);

    /**
     * 批量新增合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int insertBatch(@Param("entityList") List<CmjtPayReceInfoDTO> entityList);

    /**
     * 选择性更新合同(只更新非空字段)
     * @param cmjtPayReceInfoDTO 合同对象
     * @return 影响行数
     */
    int updateSensitive(CmjtPayReceInfoDTO cmjtPayReceInfoDTO);

    /**
     * 全量更新合同(更新所有字段)
     * @param cmjtPayReceInfoDTO 合同对象
     * @return 影响行数
     */
    int updateAll(CmjtPayReceInfoDTO cmjtPayReceInfoDTO);

    /**
     * 批量更新合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int updateBatch(@Param("entityList") List<CmjtPayReceInfoDTO> entityList);

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

    int updateMerge(CmjtPayReceInfoDTO cmjtPayReceInfoDTO);

    /**
     * 根据合同ID查询合同收付款计划
     * @param idList
     * @return
     */
    List<CmjtPayReceInfoDTO> selectByContractIds(@Param("idList") List<String> idList);
}