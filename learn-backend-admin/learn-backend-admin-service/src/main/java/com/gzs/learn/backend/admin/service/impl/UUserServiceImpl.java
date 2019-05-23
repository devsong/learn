package com.gzs.learn.backend.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzs.learn.backend.admin.common.Pagination;
import com.gzs.learn.backend.admin.core.shiro.session.CustomSessionManager;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.entity.UUser;
import com.gzs.learn.backend.admin.entity.UUserRole;
import com.gzs.learn.backend.admin.permission.bo.URoleBo;
import com.gzs.learn.backend.admin.permission.bo.UserRoleAllocationBo;
import com.gzs.learn.backend.admin.repository.UUserMapper;
import com.gzs.learn.backend.admin.repository.UUserRoleMapper;
import com.gzs.learn.backend.admin.service.UUserService;
import com.gzs.learn.backend.admin.utils.LoggerUtils;

@Service
public class UUserServiceImpl implements UUserService {
    @Autowired
    private CustomSessionManager customSessionManager;
    @Autowired
    private UUserMapper userMapper;
    @Autowired
    private UUserRoleMapper userRoleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UUser insert(UUser entity) {
        userMapper.insert(entity);
        return entity;
    }

    @Override
    public UUser insertSelective(UUser entity) {
        userMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public UUser selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UUser entity) {
        return userMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(UUser entity) {
        return userMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public UUser login(String email, String pswd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("pswd", pswd);
        UUser user = userMapper.login(map);
        return user;
    }

    @Override
    public UUser findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        PageInfo<UUser> pageInfo = PageHelper.startPage(pageNo, pageSize, true).doSelectPageInfo(() -> userMapper.findAll(resultMap));
        return new Pagination<>(pageNo, pageSize, (int) pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Map<String, Object> deleteUserById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String[] idArray = new String[] {};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[] { ids };
            }

            for (String id : idArray) {
                count += this.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateForbidUserById(Long id, Long status) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            UUser user = selectByPrimaryKey(id);
            user.setStatus(status);
            updateByPrimaryKeySelective(user);
            // 如果当前用户在线，需要标记并且踢出
            customSessionManager.forbidUserById(id, status);
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败，请刷新再试！");
            LoggerUtils.fmtError(getClass(), "禁止或者激活用户登录失败，id[%s],status[%s]", id, status);
        }
        return resultMap;
    }

    @Override
    public Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap, Integer pageNo, Integer pageSize) {
        PageInfo<UserRoleAllocationBo> pageInfo = PageHelper.startPage(pageNo, pageSize, true)
                .doSelectPageInfo(() -> userMapper.findUserAndRole(modelMap));
        return new Pagination<>(pageNo, pageSize, (int) pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<URoleBo> selectRoleByUserId(Long id) {
        return userMapper.selectRoleByUserId(id);
    }

    @Override
    public Map<String, Object> addRole2User(Long userId, String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int count = 0;
        // 先删除原有的。
        userRoleMapper.deleteByUserId(userId);
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");

            for (String rid : idArray) {
                // 这里严谨点可以判断，也可以不判断。这个{@link StringUtils 我是重写了的}
                if (StringUtils.isNotBlank(rid)) {
                    UUserRole entity = new UUserRole(userId, new Long(rid));
                    count += userRoleMapper.insertSelective(entity);
                }
            }
        }
        resultMap.put("status", 200);
        resultMap.put("message", "操作成功");
        // 清空用户的权限，迫使再次获取权限的时候，得重新加载
        TokenManager.clearUserAuthByUserId(userId);
        resultMap.put("count", count);
        System.out.println(1 / 0);
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteRoleByUserIds(String userIds) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("userIds", userIds);
            userRoleMapper.deleteRoleByUserIds(resultMap);
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        } catch (Exception e) {
            resultMap.put("status", 200);
            resultMap.put("message", "操作失败，请重试！");
        }
        return resultMap;
    }
}
