package com.gzs.learn.backend.admin.permission.bo;

import java.io.Serializable;

import com.gzs.learn.backend.admin.model.UUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleAllocationBo extends UUser implements Serializable {
    private static final long serialVersionUID = 1L;
    // Role Name列转行，以,分割
    private String roleNames;
    // Role Id列转行，以‘,’分割
    private String roleIds;
}
