package com.gzs.learn.backend.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.entity.UPermission;
import com.gzs.learn.backend.admin.service.PermissionService;
@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {
	@Autowired
	PermissionService permissionService;
	/**
	 * 权限列表
	 * @param findContent	查询内容
	 * @param pageNo		页码
	 * @param modelMap		参数回显
	 * @return
	 */
	@RequestMapping(value="index")
	public ModelAndView index(String findContent,ModelMap modelMap,Integer pageNo){
		modelMap.put("findContent", findContent);
		Pagination<UPermission> permissions = permissionService.findPage(modelMap,pageNo,pageSize);
		return new ModelAndView("permission/index","page",permissions);
	}
}
