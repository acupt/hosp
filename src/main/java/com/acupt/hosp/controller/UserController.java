package com.acupt.hosp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liujie on 2017/8/12.
 */
@RequestMapping("/api/user")
@Controller
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }
}
