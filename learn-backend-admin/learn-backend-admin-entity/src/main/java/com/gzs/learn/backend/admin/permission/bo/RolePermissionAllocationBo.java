package com.gzs.learn.backend.admin.permission.bo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RolePermissionAllocationBo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 角色ID
    private Long id;
    // 角色type
    private String type;
    // 角色Name
    private String name;
    // 权限Name列转行，以,分割
    private String permissionNames;
    // 权限Id列转行，以‘,’分割
    private String permissionIds;
}
