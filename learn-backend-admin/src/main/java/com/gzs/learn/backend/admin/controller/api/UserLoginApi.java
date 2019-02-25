package com.gzs.learn.backend.admin.controller.api;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.model.UUser;
import com.gzs.learn.backend.admin.user.manager.UserManager;
import com.gzs.learn.backend.admin.user.service.UUserService;
import com.gzs.learn.backend.admin.utils.JsonUtil;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.StringUtils;
import com.gzs.learn.backend.admin.utils.VerifyCodeUtils;

@RestController
@RequestMapping("api/u")
public class UserLoginApi extends BaseController {
    @Resource
    private UUserService userService;

    /**
     * 注册 && 登录
     * @param vcode		验证码	
     * @param entity	UUser实体
     * @return
     */
    @RequestMapping(value = "subRegister", method = RequestMethod.POST)
    public Map<String, Object> subRegister(String vcode, UUser entity) {
        resultMap.put("status", 400);
        if (!VerifyCodeUtils.verifyCode(vcode)) {
            resultMap.put("message", "验证码不正确！");
            return resultMap;
        }
        String email = entity.getEmail();

        UUser user = userService.findUserByEmail(email);
        if (null != user) {
            resultMap.put("message", "帐号|Email已经存在！");
            return resultMap;
        }
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setLastLoginTime(date);
        // 把密码md5
        entity = UserManager.md5Pswd(entity);
        // 设置有效
        entity.setStatus(UUser._1);

        entity = userService.insert(entity);
        LoggerUtils.fmtDebug(getClass(), "注册插入完毕！", JsonUtil.obj2Str(entity));
        entity = TokenManager.login(entity, Boolean.TRUE);
        LoggerUtils.fmtDebug(getClass(), "注册后，登录完毕！", JsonUtil.obj2Str(entity));
        resultMap.put("message", "注册成功！");
        resultMap.put("status", 200);
        return resultMap;
    }

    @RequestMapping(value = "submitLogin", method = RequestMethod.POST)
    public Map<String, Object> submitLogin(UUser entity, Boolean rememberMe, HttpServletRequest request) {
        entity = TokenManager.login(entity, rememberMe);
        resultMap.put("status", 200);
        resultMap.put("message", "登录成功");
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (null != savedRequest) {
            url = savedRequest.getRequestUrl();
        }
        LoggerUtils.fmtDebug(getClass(), "获取登录之前的URL:[%s]", url);
        // 如果登录之前没有地址，那么就跳转到首页。
        if (StringUtils.isBlank(url)) {
            url = request.getContextPath() + "/user/index";
        }
        // 跳转地址
        resultMap.put("back_url", url);

        return resultMap;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        TokenManager.logout();
        return "success";
    }
}
