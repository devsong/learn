package com.gzs.learn.backend.admin.common;

import java.util.Calendar;

public interface Consts {

    String REDIS_SHIRO_SESSION = "sojson-shiro-demo-session:";

    String REDIS_SHIRO_ALL = "*sojson-shiro-demo-session:*";

    String SESSION_STATUS = "sojson-online-status";

    int SESSION_VAL_TIME_SPAN = 18000;

    int DB_INDEX = 1;

    String CONTEXT_PATH = "contextPath";
    /***项目根路径*/

    /***Freemarker 使用的变量 begin**/

    String TARGET = "target";// 标签使用目标

    String OUT_TAG_NAME = "outTagName";// 输出标签Name

    /***Freemarker 使用的变量 end**/

    /**其他常用变量 begin**/
    String NAME = "name";
    String ID = "id";
    String TOKEN = "token";
    String LOING_USER = "loing_user";
    /**Long */
    Long ZERO = new Long(0);
    Long ONE = new Long(1);
    Long TWO = new Long(2);
    Long THREE = new Long(3);
    Long EIGHT = new Long(8);

    /**String */
    String S_ZERO = "0";
    String S_ONE = "1";
    String S_TOW = "2";
    String S_THREE = "3";

    /**Integer */
    Integer I_ZERO = 0;
    Integer I_ONE = 1;
    Integer I_TOW = 2;
    Integer I_THREE = 3;
    /**其他常用变量 end**/

    /**cache常用变量 begin**/
    String CACHE_NAME = "shiro_cache";
    String CACHE_MANAGER = "cacheManager";// cacheManager bean name
    /**cache常用变量 end**/

    /**当前年份**/
    int NOW_YEAY = Calendar.getInstance().get(Calendar.YEAR);

    /**地址域名**/
    String DOMAIN_WWW = IConfig.get("domain.www");// 前端域名
    String DOMAIN_CDN = IConfig.get("domain.cdn");// 静态资源域名
    String VERSION = String.valueOf(System.currentTimeMillis());// 版本号，重启的时间

    // 存储到缓存，标识用户的禁止状态，解决在线用户踢出的问题
    String EXECUTE_CHANGE_USER = "SOJSON_EXECUTE_CHANGE_USER";

    /**
     * 登录url
     */
    String LOGIN_URL = "http://www.sojson.com/user/open/toLogin.shtml";
    /**
     * 未认证url
     */
    String UNAUTHORIZED_URL = "http://www.sojson.com/unauthorized.html";

}
