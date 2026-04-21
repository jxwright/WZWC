package avicit.cmjt.feedback.dao;

import avicit.cmjt.feedback.dto.CmjtFeedbackIssueDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CmjtFeedbackIssueDAO {

    List<CmjtFeedbackIssueDTO> searchFeedbackIssue(@Param("bean") CmjtFeedbackIssueDTO bean);

    CmjtFeedbackIssueDTO findFeedbackIssueById(String id);

    int insertFeedbackIssue(CmjtFeedbackIssueDTO dto);

    int updateFeedbackIssueReply(CmjtFeedbackIssueDTO dto);

    int updateFeedbackIssueClose(CmjtFeedbackIssueDTO dto);
}
