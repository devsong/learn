package com.gzs.learn.backend.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.core.shiro.session.CustomSessionManager;
import com.gzs.learn.backend.admin.entity.UUser;
import com.gzs.learn.backend.admin.user.bo.UserOnlineBo;
import com.gzs.learn.backend.admin.user.service.UUserService;

@Controller
@RequestMapping("member")
public class MemberController extends BaseController {
    /***
     * 用户手动操作Session
     * */
    @Autowired
    private CustomSessionManager customSessionManager;
    @Autowired
    private UUserService userService;

    /**
     * 用户列表管理
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        Pagination<UUser> page = userService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("member/list");
    }

    /**
     * 在线用户管理
     * @return
     */
    @RequestMapping(value = "online")
    public ModelAndView online() {
        List<UserOnlineBo> list = customSessionManager.getAllUser();
        return new ModelAndView("member/online", "list", list);
    }

    /**
     * 在线用户详情
     * @return
     */
    @RequestMapping(value = "onlineDetails/{sessionId}", method = RequestMethod.GET)
    public ModelAndView onlineDetails(@PathVariable("sessionId") String sessionId) {
        UserOnlineBo bo = customSessionManager.getSession(sessionId);
        return new ModelAndView("member/onlineDetails", "bo", bo);
    }
}
