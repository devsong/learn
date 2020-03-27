package com.gzs.learn.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.common.Pagination;
import com.gzs.learn.backend.admin.permission.bo.UserRoleAllocationBo;
import com.gzs.learn.backend.admin.service.UUserService;

@Controller
@RequestMapping("role")
public class UserRoleAllocationController extends BaseController {
    @Autowired
    private UUserService userService;

    /**
     * 用户角色权限分配
     * @param modelMap
     * @param DEFAULT_PAGE_NO
     * @param findContent
     * @return
     */
    @RequestMapping(value = "allocation")
    public ModelAndView allocation(ModelMap modelMap, Integer pageNo, String findContent) {
        modelMap.put("findContent", findContent);
        pageNo = pageNo == null ? DEFAULT_PAGE_NO : pageNo;
        Pagination<UserRoleAllocationBo> boPage = userService.findUserAndRole(modelMap, pageNo, DEFAULT_PAGE_SIZE);
        modelMap.put("page", boPage);
        return new ModelAndView("role/allocation");
    }
}