package com.gzs.learn.backend.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserCoreController extends BaseController {
    /**
     * 个人资料
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView userIndex() {
        return new ModelAndView("user/index");
    }

    /**
     * 偷懒一下，通用页面跳转
     * @param page
     * @return
     */
    @RequestMapping(value = "{page}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("page") String page) {
        return new ModelAndView(String.format("user/%s", page));
    }
}
