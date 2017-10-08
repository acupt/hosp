package com.acupt.hosp.controller;

import com.acupt.dao.query.SickCaseQuery;
import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import com.acupt.entity.User;
import com.acupt.entity.enums.SickCaseTypeEnum;
import com.acupt.entity.es.SickCase;
import com.acupt.hosp.web.LoginContext;
import com.acupt.service.SickCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liujie on 2017/9/26.
 */
@RequestMapping({"/api/case", "/sick"})
@Controller
public class SickCaseController {

    @Resource
    private SickCaseService sickCaseService;

    @RequestMapping(value = "/case/{id}.html", method = RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request, @PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("sick/case");
        Result<SickCase> result = sickCaseService.get(id);
        return view.addObject(result).addObject(LoginContext.get(request).getUser());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request, String title, String content) {
        LoginContext loginContext = LoginContext.get(request);
        SickCase sickCase = new SickCase();
        sickCase.setTitle(title);
        sickCase.setContent(content);
        sickCase.setType(SickCaseTypeEnum.USER_SUBMIT.getValue());
        sickCase.setCt(System.currentTimeMillis());
        sickCase.setUid(loginContext.getUser().getId());
        return sickCaseService.insert(sickCase);
    }

    @RequestMapping(value = "/diagnose", method = RequestMethod.POST)
    @ResponseBody
    public Result diagnose(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNo) {
        SickCaseQuery query = new SickCaseQuery();
        User user = LoginContext.get(request).getUser();
        query.setProcessed(false);
        return sickCaseService.query(query);
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    @ResponseBody
    public Result history(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNo) {
        SickCaseQuery query = new SickCaseQuery();
        User user = LoginContext.get(request).getUser();
        if (user.isNormal()) {
            query.setUid(user.getId());
        } else if (user.isDoctor()) {
            query.setDrUid(user.getId());
        }
        return sickCaseService.query(query);
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    @ResponseBody
    public Result process(HttpServletRequest request, String id, String remarks) {
        Result<SickCase> result = sickCaseService.get(id);
        if (!result.isSuccess()) {
            return result;
        }
        if (result.getData() == null) {
            return new Result(CodeEnum.RECORD_NOT_EXIST);
        }
        User user = LoginContext.get(request).getUser();
        SickCase sickCase = result.getData();
        sickCase.setProcessed(true);
        sickCase.setDrRemarks(remarks);
        sickCase.setEt(System.currentTimeMillis());
        sickCase.setDrUid(user.getId());
        return sickCaseService.update(sickCase);
    }
}
