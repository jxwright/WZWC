package avicit.cmjt.feedback.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 问题反馈DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CmjtFeedbackIssueDTO", description = "问题反馈")
@PojoRemark(table = "CMJT_FEEDBACK_ISSUE", object = "CmjtFeedbackIssueDTO", name = "问题反馈")
public class CmjtFeedbackIssueDTO extends BeanDTO {
    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 36)
    @ApiModelProperty(value = "主键ID")
    @FieldRemark(column = "ID", field = "id", name = "主键ID")
    private String id;

    @Size(max = 200)
    @ApiModelProperty(value = "问题标题")
    @FieldRemark(column = "TITLE", field = "title", name = "问题标题")
    private String title;

    @Size(max = 2000)
    @ApiModelProperty(value = "问题内容")
    @FieldRemark(column = "QUESTION_CONTENT", field = "questionContent", name = "问题内容")
    private String questionContent;

    @Size(max = 20)
    @ApiModelProperty(value = "问题状态：OPEN/REPLIED/CLOSED")
    @FieldRemark(column = "STATUS", field = "status", name = "问题状态")
    private String status;

    @Size(max = 36)
    @ApiModelProperty(value = "提问用户ID")
    @FieldRemark(column = "USER_ID", field = "userId", name = "提问用户ID")
    private String userId;

    @Size(max = 100)
    @ApiModelProperty(value = "提问用户名")
    @FieldRemark(column = "USER_NAME", field = "userName", name = "提问用户名")
    private String userName;

    @Size(max = 2000)
    @ApiModelProperty(value = "管理员回复")
    @FieldRemark(column = "ADMIN_REPLY", field = "adminReply", name = "管理员回复")
    private String adminReply;

    @Size(max = 36)
    @ApiModelProperty(value = "回复管理员ID")
    @FieldRemark(column = "REPLY_ADMIN_ID", field = "replyAdminId", name = "回复管理员ID")
    private String replyAdminId;

    @Size(max = 100)
    @ApiModelProperty(value = "回复管理员姓名")
    @FieldRemark(column = "REPLY_ADMIN_NAME", field = "replyAdminName", name = "回复管理员姓名")
    private String replyAdminName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "回复时间")
    @FieldRemark(column = "REPLY_TIME", field = "replyTime", name = "回复时间")
    private Date replyTime;

    @Size(max = 36)
    @ApiModelProperty(value = "关闭人ID")
    @FieldRemark(column = "CLOSE_BY", field = "closeBy", name = "关闭人ID")
    private String closeBy;

    @Size(max = 100)
    @ApiModelProperty(value = "关闭人")
    @FieldRemark(column = "CLOSE_BY_NAME", field = "closeByName", name = "关闭人")
    private String closeByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "关闭时间")
    @FieldRemark(column = "CLOSE_TIME", field = "closeTime", name = "关闭时间")
    private Date closeTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creationDateBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creationDateEnd;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getQuestionContent() { return questionContent; }
    public void setQuestionContent(String questionContent) { this.questionContent = questionContent; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getAdminReply() { return adminReply; }
    public void setAdminReply(String adminReply) { this.adminReply = adminReply; }
    public String getReplyAdminId() { return replyAdminId; }
    public void setReplyAdminId(String replyAdminId) { this.replyAdminId = replyAdminId; }
    public String getReplyAdminName() { return replyAdminName; }
    public void setReplyAdminName(String replyAdminName) { this.replyAdminName = replyAdminName; }
    public Date getReplyTime() { return replyTime; }
    public void setReplyTime(Date replyTime) { this.replyTime = replyTime; }
    public String getCloseBy() { return closeBy; }
    public void setCloseBy(String closeBy) { this.closeBy = closeBy; }
    public String getCloseByName() { return closeByName; }
    public void setCloseByName(String closeByName) { this.closeByName = closeByName; }
    public Date getCloseTime() { return closeTime; }
    public void setCloseTime(Date closeTime) { this.closeTime = closeTime; }
    public Date getCreationDateBegin() { return creationDateBegin; }
    public void setCreationDateBegin(Date creationDateBegin) { this.creationDateBegin = creationDateBegin; }
    public Date getCreationDateEnd() { return creationDateEnd; }
    public void setCreationDateEnd(Date creationDateEnd) { this.creationDateEnd = creationDateEnd; }
}
