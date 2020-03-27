package com.gzs.learn.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.common.Pagination;
import com.gzs.learn.backend.admin.permission.bo.RolePermissionAllocationBo;
import com.gzs.learn.backend.admin.service.RoleService;

@Controller
@RequestMapping("permission")
public class PermissionAllocationController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 权限分配
     * @param modelMap
     * @param DEFAULT_PAGE_NO
     * @param findContent
     * @return
     */
    @RequestMapping(value = "allocation")
    public ModelAndView allocation(ModelMap modelMap, Integer pageNo, String findContent) {
        modelMap.put("findContent", findContent);
        Pagination<RolePermissionAllocationBo> boPage = roleService.findRoleAndPermissionPage(modelMap, pageNo, DEFAULT_PAGE_SIZE);
        modelMap.put("page", boPage);
        return new ModelAndView("permission/allocation");
    }
}