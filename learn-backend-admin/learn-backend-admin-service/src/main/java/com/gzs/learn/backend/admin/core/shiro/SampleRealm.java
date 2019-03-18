package com.gzs.learn.backend.admin.core.shiro;

import java.util.Date;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.core.shiro.token.ShiroToken;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.entity.UUser;
import com.gzs.learn.backend.admin.service.PermissionService;
import com.gzs.learn.backend.admin.service.RoleService;
import com.gzs.learn.backend.admin.service.UUserService;
import com.gzs.learn.backend.admin.utils.SpringContextUtil;

@Component("sampleRealm")
public class SampleRealm extends AuthorizingRealm {

    public SampleRealm() {
        super();
    }

    /**
     *  认证信息，主要针对用户登录， 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken) authcToken;
        UUserService userService = SpringContextUtil.getBean(UUserService.class);
        UUser user = userService.login(token.getUsername(), new String(token.getPassword()));
        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if (UUser._0.equals(user.getStatus())) {
            throw new DisabledAccountException("帐号已经禁止登录！");
        } else {
            user.setLastLoginTime(new Date());
            userService.updateByPrimaryKeySelective(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), getName());
    }

    /** 
    * 授权 
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = TokenManager.getUserId();
        RoleService roleService = SpringContextUtil.getBean(RoleService.class);
        PermissionService permissionService = SpringContextUtil.getBean(PermissionService.class);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roles = roleService.findRoleByUserId(userId);
        info.setRoles(roles);
        // 根据用户ID查询权限（permission），放入到Authorization里。
        Set<String> permissions = permissionService.findPermissionByUserId(userId);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 清空当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 指定principalCollection 清除
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
