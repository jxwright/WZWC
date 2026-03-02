package avicit.cmjt.cmjtsubjectbase.dao;

import avicit.cmjt.cmjtsubjectbase.dto.CmjtSubjectBaseDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：xiazx
* @邮箱：xiazx@avic-d.com
* @创建时间： 2025-04-11 09:57
* @类说明：标的物库表Dao
* @修改记录：
*/
@MyBatisRepository
public interface CmjtSubjectBaseDAO {

//    /**
//     * 批量新增对象
//     * @param list 要插入的对象列表
//     * @return int 影响的行数
//     */
//    int insertBatch(List<CmjtSubjectBaseDTO> list);
//
//    /**
//     * 批量更新对象
//     * @param list 要更新的对象列表
//     * @return int 影响的行数
//     */
//    int updateBatch(List<CmjtSubjectBaseDTO> list);
//
//    /**
//     * 分页查询
//     * @param searchParams 查询参数
//     * @return List<CmjtSubjectBaseDTO>
//     */
//    Page<CmjtSubjectBaseDTO> findListByPage(CmjtSubjectBaseDTO searchParams);
//
//    /**
//     * 分页查询
//     * @param searchParams 查询参数
//     * @return List<CmjtSubjectBaseDTO>
//     */
//    List<CmjtSubjectBaseDTO> findList(CmjtSubjectBaseDTO searchParams);
//
//    /**
//     * 分页查询
//     * @param searchParams 查询参数
//     * @return List<CmjtSubjectBaseDTO>
//     */
//    List<CmjtSubjectBaseDTO> selectList(CmjtSubjectBaseDTO searchParams);
//
//    /**
//     * 根据ID列表批量查询
//     * @param idList ID列表
//     * @return List<CmjtSubjectBaseDTO>
//     */
//    List<CmjtSubjectBaseDTO> findListByIds(List<String> idList);
//
//    /**
//     * 根据主键查询单个对象
//     * @param id
//     * @return CmjtSubjectBaseDTO
//     */
//    CmjtSubjectBaseDTO get(String id);
//
//    /**
//     * 新增对象
//     * @param dto 要插入的对象
//     * @return int 影响的行数
//     */
//    int insert(CmjtSubjectBaseDTO dto);
//
//    /**
//     * 选择性更新对象(只更新非空字段)
//     * @param dto 要更新的对象
//     * @return int 影响的行数
//     */
//    int updateSensitive(CmjtSubjectBaseDTO dto);
//
//    /**
//     * 全量更新对象(更新所有字段)
//     * @param dto 要更新的对象
//     * @return int 影响的行数
//     */
//    int updateAll(CmjtSubjectBaseDTO dto);
//
//    /**
//     * 根据主键删除
//     * @param id 主键
//     * @return int 影响的行数
//     */
//    int delete(String id);
//
//    /**
//     * 根据主键批量删除
//     * @param ids 主键列表
//     * @return int 影响的行数
//     */
//    int deleteByIds(String[] ids);

     /**
     * 批量删除数据
     * @param ids 要删除的ID集合
     * @return 删除的记录数
     */
    int deleteByIds(List<String> ids);

    /**
     * 分页查询
     * @param cmjtSubjectBaseDTO 查询对象
     * @param orderBy 排序条件
     * @param keyWord 关键字
     * @return Page<CmjtSubjectBaseDTO>
     */
    public Page<CmjtSubjectBaseDTO> searchCmjtSubjectBaseByPage(@Param("bean") CmjtSubjectBaseDTO cmjtSubjectBaseDTO, @Param("pOrderBy") String orderBy, @Param("keyWord") String keyWord);

    /**
     * 不分页查询
     * @param cmjtSubjectBaseDTO 查询对象
     * @return List<CmjtSubjectBaseDTO>
     */
    public List<CmjtSubjectBaseDTO> searchCmjtSubjectBase(@Param("bean") CmjtSubjectBaseDTO cmjtSubjectBaseDTO);

    /**
     * 主键查询
     * @param id 主键id
     * @return CmjtSubjectBaseDTO
     */
    public CmjtSubjectBaseDTO findCmjtSubjectBaseById(String id);

    /**
     * 新增
     * @param cmjtSubjectBaseDTO 保存对象
     * @return int
     */
    public int insertCmjtSubjectBase(CmjtSubjectBaseDTO cmjtSubjectBaseDTO);

    /**
     * 批量新增
     * @param list 保存对象集合
     * @return int
     */
    public int insertCmjtSubjectBaseList(List<CmjtSubjectBaseDTO> list);

    /**
     * 部分更新
     * @param cmjtSubjectBaseDTO 更新对象
     * @return int
     */
    public int updateCmjtSubjectBaseSensitive(CmjtSubjectBaseDTO cmjtSubjectBaseDTO);

    /**
     * 全部更新
     * @param cmjtSubjectBaseDTO 更新对象
     * @return int
     */
    public int updateCmjtSubjectBaseAll(CmjtSubjectBaseDTO cmjtSubjectBaseDTO);

    /**
     * 批量更新
     * @param dtoList 批量更新对象集合
     * @return int
     */
    public int updateCmjtSubjectBaseList(@Param("dtoList") List<CmjtSubjectBaseDTO> dtoList);

    /**
     * 部分更新
     * @param cmjtSubjectBaseDTO 更新对象
     * @return int
     */
    public int updateSensitiveBySourceAndBusinessId(CmjtSubjectBaseDTO cmjtSubjectBaseDTO);

    /**
     * 删除
     * @param id 主键id
     * @return int
     */
    public int deleteCmjtSubjectBaseById(String id);

    /**
     * 数据来源+数据ID查询
     * @param dataSource 数据id
     * @param businessId 数据id
     * @return CmjtSubjectBaseDTO
     */
    public int deleteCmjtSubjectBaseBySourceAndBusinessId(@Param("dataSource") String dataSource, @Param("businessId") String businessId);

    /**
     * 批量删除
     * @param idList 主键集合
     * @return int
     */
    public int deleteCmjtSubjectBaseList(List<String> idList);

}