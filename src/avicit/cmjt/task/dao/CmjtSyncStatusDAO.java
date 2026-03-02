package avicit.cmjt.task.dao;


import avicit.cmjt.task.dto.CmjtSyncStatusDTO;
import avicit.cust.domain.customer.entity.Cust;
import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface CmjtSyncStatusDAO {
    CmjtSyncStatusDTO findBySystemName(@Param("systemName") String systemName);
    int insert(CmjtSyncStatusDTO dto);
    int update(CmjtSyncStatusDTO dto);
    int updateCustMerge(Cust cust);
}
