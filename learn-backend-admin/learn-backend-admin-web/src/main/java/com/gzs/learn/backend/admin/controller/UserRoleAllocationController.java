package com.gzs.learn.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.permission.bo.UserRoleAllocationBo;
import com.gzs.learn.backend.admin.permission.service.PermissionService;
import com.gzs.learn.backend.admin.user.service.UUserService;

@Controller
@RequestMapping("role")
public class UserRoleAllocationController extends BaseController {
    @Autowired
    UUserService userService;
    @Autowired
    PermissionService permissionService;

    /**
     * 用户角色权限分配
     * @param modelMap
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "allocation")
    public ModelAndView allocation(ModelMap modelMap, Integer pageNo, String findContent) {
        modelMap.put("findContent", findContent);
        Pagination<UserRoleAllocationBo> boPage = userService.findUserAndRole(modelMap, pageNo, pageSize);
        modelMap.put("page", boPage);
        return new ModelAndView("role/allocation");
    }
}
