package com.gzs.learn.backend.admin.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gzs.learn.backend.admin.entity.URole;
import com.gzs.learn.backend.admin.permission.bo.RolePermissionAllocationBo;

public interface URoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

    Set<String> findRoleByUserId(Long id);

    List<URole> findNowAllPermission(Map<String, Object> map);

    List<URole> findAll(Map<String, Object> params);

    List<RolePermissionAllocationBo> findRoleAndPermission(Map<String, Object> params);
}