package com.gzs.learn.backend.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.permission.bo.RolePermissionAllocationBo;
import com.gzs.learn.backend.admin.permission.bo.UPermissionBo;
import com.gzs.learn.backend.admin.permission.service.PermissionService;
import com.gzs.learn.backend.admin.permission.service.RoleService;
@Controller
@RequestMapping("permission")
public class PermissionAllocationController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	/**
	 * 权限分配
	 * @param modelMap
	 * @param pageNo
	 * @param findContent
	 * @return
	 */
	@RequestMapping(value="allocation")
	public ModelAndView allocation(ModelMap modelMap,Integer pageNo,String findContent){
		modelMap.put("findContent", findContent);
		Pagination<RolePermissionAllocationBo> boPage = roleService.findRoleAndPermissionPage(modelMap,pageNo,pageSize);
		modelMap.put("page", boPage);
		return new ModelAndView("permission/allocation");
	}
}
