package com.gzs.learn.backend.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzs.learn.backend.admin.common.Pagination;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.entity.URole;
import com.gzs.learn.backend.admin.permission.bo.RolePermissionAllocationBo;
import com.gzs.learn.backend.admin.repository.URoleMapper;
import com.gzs.learn.backend.admin.service.RoleService;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.StringUtils;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private URoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(URole record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(URole record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public URole selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(URole record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(URole record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Pagination<URole> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        PageInfo<URole> pageInfo = PageHelper.startPage(pageNo, pageSize, true).doSelectPageInfo(() -> roleMapper.findAll(resultMap));
        return new Pagination<>(pageNo, pageSize, (int) pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageInfo<RolePermissionAllocationBo> pageInfo = PageHelper.startPage(pageNo, pageSize, true)
                .doSelectPageInfo(() -> roleMapper.findRoleAndPermission(params));
        return new Pagination<>(pageNo, pageSize, (int) pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[] {};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[] { ids };
            }

            for (String idx : idArray) {
                Long id = new Long(idx);
                if (Objects.equals(id, 1L)) {
                    resultMsg = "操作成功，But'系统管理员不能删除。";
                    continue;
                } else {
                    count += this.deleteByPrimaryKey(id);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public Set<String> findRoleByUserId(Long userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public List<URole> findNowAllPermission() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", TokenManager.getUserId());
        return roleMapper.findNowAllPermission(map);
    }
}
