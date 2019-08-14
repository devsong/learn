package com.gzs.learn.springframework.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
@Table(name = "sys_login_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    /**
     * 日志名称
     */
    private String logname;
    /**
     * 管理员id
     */
    private Long userid;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 是否执行成功
     */
    private String succeed;
    /**
     * 具体消息
     */
    private String message;
    /**
     * 登录ip
     */
    private String ip;
    /**
     * 更新时间戳
     */
    private Date timestamp;
}
