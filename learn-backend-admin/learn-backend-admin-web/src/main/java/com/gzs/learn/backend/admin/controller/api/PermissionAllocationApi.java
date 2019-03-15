package com.gzs.learn.backend.admin.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.permission.bo.UPermissionBo;
import com.gzs.learn.backend.admin.service.PermissionService;

@RestController
@RequestMapping("api/permission")
public class PermissionAllocationApi extends BaseController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 根据角色ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value = "selectPermissionById")
    public List<UPermissionBo> selectPermissionById(Long id) {
        List<UPermissionBo> permissionBos = permissionService.selectPermissionById(id);
        return permissionBos;
    }

    /**
     * 操作角色的权限
     * @param roleId 	角色ID
     * @param ids		权限ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "addPermission2Role")
    public Map<String, Object> addPermission2Role(Long roleId, String ids) {
        return permissionService.addPermission2Role(roleId, ids);
    }

    /**
     * 根据角色id清空权限。
     * @param roleIds	角色ID ，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "clearPermissionByRoleIds")
    public Map<String, Object> clearPermissionByRoleIds(String roleIds) {
        return permissionService.deleteByRids(roleIds);
    }
}
