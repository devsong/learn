package com.gzs.learn.backend.admin.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class BaseController {
    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    protected final static Logger log = LoggerFactory.getLogger(BaseController.class);
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    public static String URL404 = "/404.html";

    protected String pageSizeName = "DEFAULT_PAGE_SIZE";

    protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
        request.setAttribute(key, value);
    }

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public ModelAndView redirect(String redirectUrl, Map<String, Object> params) {
        ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
        if (null != params && params.size() > 0) {
            view.addAllObjects(params);
        }
        return view;
    }

    public ModelAndView redirect404() {
        return new ModelAndView(new RedirectView(URL404));
    }
}
