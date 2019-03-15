package com.gzs.learn.backend.admin.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.permission.bo.URoleBo;
import com.gzs.learn.backend.admin.user.service.UUserService;

@RestController
@RequestMapping("api/role")
public class UserRoleAllocationApi extends BaseController {
    @Autowired
    private UUserService userService;

    /**
     * 根据用户ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRoleByUserId")
    public List<URoleBo> selectRoleByUserId(Long id) {
        List<URoleBo> bos = userService.selectRoleByUserId(id);
        return bos;
    }

    /**
     * 操作用户的角色
     * @param userId 	用户ID
     * @param ids		角色ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "addRole2User")
    public Map<String, Object> addRole2User(Long userId, String ids) {
        return userService.addRole2User(userId, ids);
    }

    /**
     * 根据用户id清空角色。
     * @param userIds	用户ID ，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "clearRoleByUserIds")
    public Map<String, Object> clearRoleByUserIds(String userIds) {
        return userService.deleteRoleByUserIds(userIds);
    }
}
