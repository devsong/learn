package com.gzs.learn.backend.admin.common;

import lombok.Getter;

/**
 * 仿http请求错误码,同时为了甄别http请求错误进行扩展
 * @author guanzhisong
 *
 */
@Getter
public enum ResponseCode {
    /**
     * 接口正常返回200xx
     */
    SUCCESS(20000, "success"),

    /**
     * 客户端参数错误400xx
     */
    CLIENT_ERROR(40000, "client error"),

    /**
     * 系统异常500xx
     */
    INTERNAL_ERROR(50000, "internal server error"),

    /**
     * 业务异常6xxxx
     */
    BUSINESS_ERROR(60000, "business error"),

    ACCOUNT_DISABLE(60001, "账号已禁用"),
    
    ;
    private int code;
    private String desc;

    private ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
