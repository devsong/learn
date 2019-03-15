package com.gzs.learn.backend.admin.user.bo;

import java.io.Serializable;
import java.util.Date;

import com.gzs.learn.backend.admin.entity.UUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserOnlineBo extends UUser implements Serializable {
    private static final long serialVersionUID = 1L;
    // Session Id
    private String sessionId;
    // Session Host
    private String host;
    // Session创建时间
    private Date startTime;
    // Session最后交互时间
    private Date lastAccess;
    // Session timeout
    private long timeout;
    // session 是否踢出
    private boolean sessionStatus = Boolean.TRUE;

    public UserOnlineBo() {
    }

    public UserOnlineBo(UUser user) {
        super(user);
    }
}
