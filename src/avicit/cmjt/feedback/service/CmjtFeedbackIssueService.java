package avicit.cmjt.feedback.service;

import avicit.cmjt.feedback.dao.CmjtFeedbackIssueDAO;
import avicit.cmjt.feedback.dto.CmjtFeedbackIssueDTO;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CmjtFeedbackIssueService {

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_REPLIED = "REPLIED";
    public static final String STATUS_CLOSED = "CLOSED";

    @Autowired
    private CmjtFeedbackIssueDAO cmjtFeedbackIssueDAO;

    public List<CmjtFeedbackIssueDTO> queryIssueList(CmjtFeedbackIssueDTO query) {
        return cmjtFeedbackIssueDAO.searchFeedbackIssue(query);
    }

    public CmjtFeedbackIssueDTO queryById(String id) {
        return cmjtFeedbackIssueDAO.findFeedbackIssueById(id);
    }

    @Transactional
    public String createIssue(CmjtFeedbackIssueDTO dto) {
        if (StringUtils.isBlank(dto.getTitle()) || StringUtils.isBlank(dto.getQuestionContent())) {
            throw new IllegalArgumentException("问题标题和问题内容不能为空");
        }
        if (StringUtils.isBlank(dto.getUserId())) {
            throw new IllegalArgumentException("提问用户ID不能为空");
        }
        dto.setId(ComUtil.getId());
        dto.setStatus(STATUS_OPEN);
        PojoUtil.setSysProperties(dto, PlatformConstant.OpType.insert);
        cmjtFeedbackIssueDAO.insertFeedbackIssue(dto);
        return dto.getId();
    }

    @Transactional
    public void replyIssue(String id, String replyContent, String adminId, String adminName) {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(replyContent)) {
            throw new IllegalArgumentException("问题ID和回复内容不能为空");
        }
        CmjtFeedbackIssueDTO dbData = queryById(id);
        if (dbData == null) {
            throw new IllegalArgumentException("问题不存在");
        }
        if (STATUS_CLOSED.equals(dbData.getStatus())) {
            throw new IllegalArgumentException("问题已关闭，不能再回复");
        }
        CmjtFeedbackIssueDTO update = new CmjtFeedbackIssueDTO();
        update.setId(id);
        update.setAdminReply(replyContent);
        update.setReplyAdminId(adminId);
        update.setReplyAdminName(adminName);
        update.setReplyTime(new Date());
        update.setStatus(STATUS_REPLIED);
        PojoUtil.setSysProperties(update, PlatformConstant.OpType.update);
        cmjtFeedbackIssueDAO.updateFeedbackIssueReply(update);
    }

    @Transactional
    public void closeIssue(String id, String adminId, String adminName) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("问题ID不能为空");
        }
        CmjtFeedbackIssueDTO dbData = queryById(id);
        if (dbData == null) {
            throw new IllegalArgumentException("问题不存在");
        }
        CmjtFeedbackIssueDTO update = new CmjtFeedbackIssueDTO();
        update.setId(id);
        update.setStatus(STATUS_CLOSED);
        update.setCloseBy(adminId);
        update.setCloseByName(adminName);
        update.setCloseTime(new Date());
        PojoUtil.setSysProperties(update, PlatformConstant.OpType.update);
        cmjtFeedbackIssueDAO.updateFeedbackIssueClose(update);
    }
}
