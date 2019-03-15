package com.gzs.learn.backend.admin.core.shiro.filter;

import static com.gzs.learn.backend.admin.common.Consts.SESSION_STATUS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.utils.JsonUtil;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.core.shiro.session.SessionStatus;

@Component("simple")
public class SimpleAuthFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String url = httpRequest.getRequestURI();
        if (url.startsWith("/open/")) {
            return Boolean.TRUE;
        }
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        Map<String, String> resultMap = new HashMap<String, String>();
        SessionStatus sessionStatus = (SessionStatus) session.getAttribute(SESSION_STATUS);
        if (null != sessionStatus && !sessionStatus.isOnlineStatus()) {
            // 判断是不是Ajax请求
            if (ShiroFilterUtils.isAjax(request)) {
                LoggerUtils.debug(getClass(), "当前用户已经被踢出，并且是Ajax请求！");
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经被踢出，请重新登录！");
                out(response, resultMap);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 先退出
        Subject subject = getSubject(request, response);
        subject.logout();
        WebUtils.saveRequest(request);
        // 再重定向
        WebUtils.issueRedirect(request, response, "/open/kickedOut");
        return false;
    }

    private void out(ServletResponse hresponse, Map<String, String> resultMap) throws IOException {
        hresponse.setCharacterEncoding("UTF-8");
        PrintWriter out = hresponse.getWriter();
        out.println(JsonUtil.obj2Str(resultMap));
        out.flush();
        out.close();
    }
}
