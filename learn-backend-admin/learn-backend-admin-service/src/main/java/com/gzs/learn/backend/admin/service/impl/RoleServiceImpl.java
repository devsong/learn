package com.gzs.learn.backend.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzs.learn.backend.admin.core.mybatis.BaseMybatisDao;
import com.gzs.learn.backend.admin.core.mybatis.page.Pagination;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.entity.URole;
import com.gzs.learn.backend.admin.permission.bo.RolePermissionAllocationBo;
import com.gzs.learn.backend.admin.repository.URoleMapper;
import com.gzs.learn.backend.admin.repository.URolePermissionMapper;
import com.gzs.learn.backend.admin.repository.UUserMapper;
import com.gzs.learn.backend.admin.service.RoleService;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.StringUtils;

@Service
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseMybatisDao<URoleMapper> implements RoleService {
    @Autowired
    URoleMapper roleMapper;
    @Autowired
    UUserMapper userMapper;
    @Autowired
    URolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(URole record) {
        roleMapper.insert(record);
        throw new NullPointerException();
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
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> resultMap, Integer pageNo,
            Integer pageSize) {
        return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);
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

            c: for (String idx : idArray) {
                Long id = new Long(idx);
                if (new Long(1).equals(id)) {
                    resultMsg = "操作成功，But'系统管理员不能删除。";
                    continue c;
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

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
