package avicit.cmjt.feedback.controller;

import avicit.cmjt.feedback.dto.CmjtFeedbackIssueDTO;
import avicit.cmjt.feedback.service.CmjtFeedbackIssueService;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.core.properties.PlatformConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("avicit/cmjt/feedback/cmjtFeedbackIssueController")
public class CmjtFeedbackIssueController {

    @Autowired
    private CmjtFeedbackIssueService cmjtFeedbackIssueService;

    @RequestMapping(value = "toUserFeedbackPage")
    public ModelAndView toUserFeedbackPage() {
        return new ModelAndView("avicit/cmjt/feedback/UserFeedbackManage");
    }

    @RequestMapping(value = "toAdminFeedbackPage")
    public ModelAndView toAdminFeedbackPage() {
        return new ModelAndView("avicit/cmjt/feedback/AdminFeedbackManage");
    }

    @RequestMapping(value = "/operation/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createIssue(@RequestBody CmjtFeedbackIssueDTO dto, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(dto.getOrgIdentity())) {
                dto.setOrgIdentity(SessionHelper.getCurrentOrgIdentity(request));
            }
            String id = cmjtFeedbackIssueService.createIssue(dto);
            result.put("flag", PlatformConstant.OpResult.success);
            result.put("id", id);
        } catch (Exception e) {
            result.put("flag", PlatformConstant.OpResult.failure);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/operation/list")
    @ResponseBody
    public Map<String, Object> listIssue(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            CmjtFeedbackIssueDTO query = new CmjtFeedbackIssueDTO();
            query.setUserId(ServletRequestUtils.getStringParameter(request, "userId", ""));
            query.setStatus(ServletRequestUtils.getStringParameter(request, "status", ""));
            query.setTitle(ServletRequestUtils.getStringParameter(request, "title", ""));
            query.setOrgIdentity(ServletRequestUtils.getStringParameter(request, "orgIdentity", SessionHelper.getCurrentOrgIdentity(request)));
            List<CmjtFeedbackIssueDTO> rows = cmjtFeedbackIssueService.queryIssueList(query);
            result.put("flag", PlatformConstant.OpResult.success);
            result.put("rows", rows);
        } catch (Exception e) {
            result.put("flag", PlatformConstant.OpResult.failure);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/operation/reply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> replyIssue(@RequestBody Map<String, String> body) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            cmjtFeedbackIssueService.replyIssue(body.get("id"), body.get("replyContent"), body.get("adminId"), body.get("adminName"));
            result.put("flag", PlatformConstant.OpResult.success);
        } catch (Exception e) {
            result.put("flag", PlatformConstant.OpResult.failure);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/operation/close", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> closeIssue(@RequestBody Map<String, String> body) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            cmjtFeedbackIssueService.closeIssue(body.get("id"), body.get("adminId"), body.get("adminName"));
            result.put("flag", PlatformConstant.OpResult.success);
        } catch (Exception e) {
            result.put("flag", PlatformConstant.OpResult.failure);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
