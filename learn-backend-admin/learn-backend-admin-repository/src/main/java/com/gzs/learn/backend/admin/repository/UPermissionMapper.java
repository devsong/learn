package com.gzs.learn.backend.admin.repository;

import java.util.List;
import java.util.Set;

import com.gzs.learn.backend.admin.entity.UPermission;
import com.gzs.learn.backend.admin.permission.bo.UPermissionBo;

public interface UPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    UPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

    List<UPermissionBo> selectPermissionById(Long id);

    Set<String> findPermissionByUserId(Long id);
}