package com.gzs.learn.backend.admin.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.entity.UPermission;
import com.gzs.learn.backend.admin.service.PermissionService;


@RequestMapping("api/permission")
@RestController
public class PermissionApi extends BaseController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 权限添加
     * @param role
     * @return
     */
    @RequestMapping(value = "addPermission", method = RequestMethod.POST)
    public UPermission addPermission(UPermission psermission) {
        UPermission entity = permissionService.insertSelective(psermission);
        return entity;
    }

    /**
     * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
     * @param id
     * @return
     */
    @RequestMapping(value = "deletePermissionById", method = RequestMethod.POST)
    public Map<String, Object> deleteRoleById(String ids) {
        return permissionService.deletePermissionById(ids);
    }
}
