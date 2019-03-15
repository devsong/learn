package com.gzs.learn.backend.admin.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.entity.URole;
import com.gzs.learn.backend.admin.service.RoleService;
import com.gzs.learn.backend.admin.service.manager.UserManager;

@RestController
@RequestMapping("api/role")
public class RoleApi extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色添加
     * @param role
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public Map<String, Object> addRole(URole role) {
        int count = roleService.insertSelective(role);
        resultMap.put("successCount", count);
        return resultMap;
    }

    /**
     * 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除。
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteRoleById", method = RequestMethod.POST)
    public Map<String, Object> deleteRoleById(String ids) {
        return roleService.deleteRoleById(ids);
    }

    /**
     * 我的权限 bootstrap tree data
     * @return
     */
    @RequestMapping(value = "getPermissionTree", method = RequestMethod.POST)
    public List<Map<String, Object>> getPermissionTree() {
        // 查询我所有的角色 ---> 权限
        List<URole> roles = roleService.findNowAllPermission();
        // 把查询出来的roles 转换成bootstarp 的 tree数据
        List<Map<String, Object>> data = UserManager.toTreeData(roles);
        return data;
    }
}
