package com.gzs.learn.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.model.URole;
import com.gzs.learn.backend.admin.permission.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(String findContent, ModelMap modelMap) {
        modelMap.put("findContent", findContent);
        Pagination<URole> role = roleService.findPage(modelMap, pageNo, pageSize);
        return new ModelAndView("role/index", "page", role);
    }

    /**
     * 我的权限页面
     * @return
     */
    @RequestMapping(value = "mypermission", method = RequestMethod.GET)
    public ModelAndView mypermission() {
        return new ModelAndView("permission/mypermission");
    }
}
