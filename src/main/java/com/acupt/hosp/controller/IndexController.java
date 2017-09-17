package com.acupt.hosp.controller;

import com.acupt.dao.SickCaseDAO;
import com.acupt.dao.SicknessDAO;
import com.acupt.domain.Result;
import com.acupt.entity.Sickness;
import com.acupt.entity.User;
import com.acupt.entity.es.SickCase;
import com.acupt.hosp.web.LoginContext;
import com.acupt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by liujie on 2017/8/12.
 */
@RequestMapping("/")
@Controller
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private SickCaseDAO sickCaseDAO;

    @Resource
    private SicknessDAO sicknessDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/*.html", method = RequestMethod.GET)
    public ModelAndView html(HttpServletRequest request) {
        String uri = request.getRequestURI();
        uri = uri.substring(1, uri.length() - 5);
        ModelAndView view = new ModelAndView(uri);
        LoginContext loginContext = LoginContext.get(request);
        view.addObject("ctx", loginContext);
        Enumeration<String> name = request.getParameterNames();
        while (name.hasMoreElements()) {
            String k = name.nextElement();
            view.addObject(k, request.getParameter(k));
        }
        return view;
    }

    @RequestMapping(value = "/*/*.html", method = RequestMethod.GET)
    public ModelAndView html2(HttpServletRequest request) {
        return html(request);
    }

    @RequestMapping(value = "/*/*/*.html", method = RequestMethod.GET)
    public ModelAndView html3(HttpServletRequest request) {
        return html(request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result index(HttpServletRequest request, String username, String password) {
        Result<User> result = userService.login(username, password);
        if (result.isSuccess()) {
            LoginContext.login(request, result.getData());
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        LoginContext.logout(request);
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(String key) {
        ModelAndView view = new ModelAndView("search");
        List<SickCase> list = sickCaseDAO.findByContent(key);
        view.addObject("sicks", list);
        view.addObject("key", key);
        return view;
    }

    @RequestMapping(value = "/wiki", method = RequestMethod.GET)
    public ModelAndView wiki(long id) {
        ModelAndView view = new ModelAndView("sick/info");
        Sickness sickness = sicknessDAO.getOne(id);
        view.addObject("sick", sickness);
        return view;
    }
}
