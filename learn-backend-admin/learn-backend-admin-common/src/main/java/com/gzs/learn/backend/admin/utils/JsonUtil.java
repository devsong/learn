package com.gzs.learn.backend.admin.utils;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static boolean FASTJSON = false;
    private static final String FASTJSON_CLASS = "com.alibaba.fastjson.JSON";
    private static boolean JACKSON = false;
    private static final String JACKSON_CLASS = "com.fasterxml.jackson.databind.ObjectMapper";
    private static Object JACKSON_INSTANCE = null;
    static {
        try {
            Class.forName(FASTJSON_CLASS);
            FASTJSON = true;
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName(JACKSON_CLASS);
            JACKSON = true;
            JACKSON_INSTANCE = Class.forName(JACKSON_CLASS).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

        }
    }

    public static String obj2Str(Object obj) {
        if (obj == null) {
            return null;
        }
        if (FASTJSON) {
            return JSON.toJSONString(obj);
        }
        if (JACKSON) {
            try {
                return ((ObjectMapper) JACKSON_INSTANCE).writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
        throw new IllegalArgumentException("can not find json serializable provider in classpath");
    }

    public static <T> T str2Obj(String str, Class<T> cls) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (FASTJSON) {
            return JSON.parseObject(str, cls);
        }
        if (JACKSON) {
            try {
                return ((ObjectMapper) JACKSON_INSTANCE).readValue(str, cls);
            } catch (IOException e) {
                return null;
            }
        }
        throw new IllegalArgumentException("can not find json serializable provider in classpath");
    }
}
