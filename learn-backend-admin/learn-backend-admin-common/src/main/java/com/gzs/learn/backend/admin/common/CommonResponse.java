package com.gzs.learn.backend.admin.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> CommonResponse<T> build(int code, String msg, T data) {
        return new CommonResponse<T>(code, msg, data);
    }

    public static <T> CommonResponse<T> buildSuccess(T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> CommonResponse<T> buildSuccess(String msg, T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }
}
