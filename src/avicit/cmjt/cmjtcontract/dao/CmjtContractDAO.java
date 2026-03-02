package avicit.cmjt.cmjtcontract.dao;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtcontract.dto.CmjtContractResposeDTO;
import avicit.cmjt.cmjtsubjectinfo.dto.CmjtSubjectInfoDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 11:31
* @类说明：合同主表Dao
* @修改记录：
*/
@MyBatisRepository
public interface CmjtContractDAO {

    /**
     * 分页查询
     * @param cmjtContractDTO 查询对象
     * @param orderBy 排序条件
     * @param keyWord 关键字
     * @return Page<DynCmjtContractDTO>
     */
    Page<CmjtContractDTO> searchCmjtContractByPage(@Param("bean") CmjtContractDTO cmjtContractDTO, @Param("pOrderBy") String orderBy, @Param("keyWord") String keyWord);

    /**
     * 分页查询合同列表
     * @param searchParams
     * @return
     */
    Page<CmjtContractDTO> findListByPage(@Param("bean") CmjtContractDTO searchParams);

    /**
     * 分页查询合同列表
     * @param cmjtContractDTO 查询参数
     * @return 合同列表
     */
    List<CmjtContractDTO> findList(@Param("bean") CmjtContractDTO cmjtContractDTO);

    /**
     * 根据ID列表批量查询合同
     * @param idList ID列表
     * @return 合同列表
     */
    List<CmjtContractDTO> findListByIds(@Param("idList") List<String> idList);

    /**
     * 根据ID查询合同详情
     * @param id 合同ID
     * @return 合同详情
     */
    CmjtContractDTO get(@Param("id") String id);

    /**
     * 新增合同
     * @param cmjtContractDTO 合同对象
     * @return 影响行数
     */
    int insert(CmjtContractDTO cmjtContractDTO);

    /**
     * 批量新增合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int insertBatch(@Param("entityList") List<CmjtContractDTO> entityList);

    /**
     * 选择性更新合同(只更新非空字段)
     * @param cmjtContractDTO 合同对象
     * @return 影响行数
     */
    int updateSensitive(CmjtContractDTO cmjtContractDTO);

    /**
     * 全量更新合同(更新所有字段)
     * @param cmjtContractDTO 合同对象
     * @return 影响行数
     */
    int updateAll(CmjtContractDTO cmjtContractDTO);

    /**
     * 批量更新合同
     * @param entityList 合同列表
     * @return 影响行数
     */
    int updateBatch(@Param("entityList") List<CmjtContractDTO> entityList);

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
    int updateMerge(CmjtContractDTO cmjtContractDTO);

    List<CmjtContractDTO>findContractsToSync();

    int updateSyncStatus(@Param("contractId")String contractId,@Param("syncStatus")String syncStatus,@Param("msg")String msg);


    List<CmjtContractResposeDTO>selectCmjtContractByDate(@Param("startDate")String startDate, @Param("endDate")String endDate);
}