package com.gzs.learn.backend.admin.core.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public ShiroToken(String username, String pswd) {
        super(username, pswd);
    }
}
