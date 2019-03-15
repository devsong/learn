package com.gzs.learn.backend.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.service.UUserService;

@Controller
@RequestMapping("u")
public class UserLoginController extends BaseController {
    @Resource
    private UUserService userService;

    /**
     * 登录跳转
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    /**
     * 注册跳转
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("user/register");
    }
}
