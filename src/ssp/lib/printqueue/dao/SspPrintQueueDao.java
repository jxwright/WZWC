package ssp.lib.printqueue.dao;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Gaofeicm
 */
@MyBatisRepository
public interface SspPrintQueueDao {
    Map<String,Object> getFormSetPrintInfo(String fromCode);
    void removeFromQueue(Map<String,Object> param);

    String getTaskIdByDocInfo(@Param("dataId") String dataId,@Param("dataSrc") String dataSrc);
}

