package avicit.cmjt.controller;


import avicit.cmjt.utils.ContractService;
import avicit.platform6.core.spring.SpringFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ssp.utils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping({"cmjt/cmClientController"})
public class CmClientController {

    @RequestMapping({"/requestUrl"})
    @ResponseBody
    public Map<String, Object> getConfig(HttpServletRequest request) {
        HashMap map = new HashMap();

        try {
//            Map<String,String> param = new HashMap<>();
//            param.put("creationDateBegin","2024-12-31");
//            param.put("creationDateEnd","2025-12-31");
//            param.put("orgCode","131320");
//            map.put("result", ContractService.queryContracts(param));
//            map.put("flag", true);
            Map<String,Object> param = new HashMap<>();
            param.put("businessId","");
            param.put("creationDateBegin","2024-12-31");
            param.put("creationDateEnd","2025-12-31");
            param.put("orgCode","131320");
            map.put("result", ContractService.querySubjects(param));
            map.put("flag", true);
        } catch (Exception var8) {
            var8.printStackTrace();
            map.put("result", false);
            map.put("file", var8.getMessage());
        }

        return map;
    }

}
