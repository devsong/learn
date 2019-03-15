package com.gzs.learn.backend.admin.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.core.shiro.session.CustomSessionManager;
import com.gzs.learn.backend.admin.user.service.UUserService;

@RestController
@RequestMapping("api/member")
public class MemberApi extends BaseController {
    /***
     * 用户手动操作Session
     */
    @Autowired
    private CustomSessionManager customSessionManager;
    @Autowired
    private UUserService userService;

    /**
     * 改变Session状态
     * @param status
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "changeSessionStatus", method = RequestMethod.POST)
    public Map<String, Object> changeSessionStatus(Boolean status, String sessionIds) {
        return customSessionManager.changeSessionStatus(status, sessionIds);
    }

    /**
     * 根据ID删除，
     * @param ids	如果有多个，以“,”间隔。
     * @return
     */
    @RequestMapping(value = "deleteUserById", method = RequestMethod.POST)
    public Map<String, Object> deleteUserById(String ids) {
        return userService.deleteUserById(ids);
    }

    /**
     * 禁止登录
     * @param id		用户ID
     * @param status	1:有效，0:禁止登录
     * @return
     */
    @RequestMapping(value = "forbidUserById", method = RequestMethod.POST)
    public Map<String, Object> forbidUserById(Long id, Long status) {
        return userService.updateForbidUserById(id, status);
    }
}
