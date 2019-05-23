package com.gzs.learn.backend.admin.repository;

import java.util.List;
import java.util.Map;

import com.gzs.learn.backend.admin.entity.UUser;
import com.gzs.learn.backend.admin.permission.bo.URoleBo;
import com.gzs.learn.backend.admin.permission.bo.UserRoleAllocationBo;

public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    UUser login(Map<String, Object> map);

    UUser findUserByEmail(String email);

    List<URoleBo> selectRoleByUserId(Long id);

    List<UUser> findAll(Map<String, Object> params);

    List<UserRoleAllocationBo> findUserAndRole(Map<String, Object> params);

}