package com.gzs.learn.backend.admin.permission.bo;

import java.io.Serializable;

import com.gzs.learn.backend.admin.model.UPermission;
import com.gzs.learn.backend.admin.utils.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UPermissionBo extends UPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否勾选
     */
    private String marker;
    /**
     * role Id
     */
    private String roleId;

    public boolean isCheck() {
        return StringUtils.equals(roleId, marker);
    }
}
