package com.gzs.learn.backend.admin.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzs.learn.backend.admin.controller.BaseController;
import com.gzs.learn.backend.admin.core.shiro.token.manager.TokenManager;
import com.gzs.learn.backend.admin.model.UUser;
import com.gzs.learn.backend.admin.user.manager.UserManager;
import com.gzs.learn.backend.admin.user.service.UUserService;

@RestController
@RequestMapping("api/user")
public class UserCoreApi extends BaseController {
    @Autowired
    private UUserService userService;

    /**
     * 密码修改
     * @return
     */
    @RequestMapping(value = "updatePswd", method = RequestMethod.POST)
    public Map<String, Object> updatePswd(String pswd, String newPswd) {
        // 根据当前登录的用户帐号 + 老密码，查询。
        String email = TokenManager.getToken().getEmail();
        pswd = UserManager.md5Pswd(email, pswd);
        UUser user = userService.login(email, pswd);

        if ("admin".equals(email)) {
            resultMap.put("status", 300);
            resultMap.put("message", "管理员不准修改密码。");
            return resultMap;
        }

        if (null == user) {
            resultMap.put("status", 300);
            resultMap.put("message", "密码不正确！");
            return resultMap;
        }
        user.setPswd(newPswd);
        // 加工密码
        user = UserManager.md5Pswd(user);
        // 修改密码
        userService.updateByPrimaryKeySelective(user);
        resultMap.put("status", 200);
        resultMap.put("message", "修改成功!");
        // 重新登录一次
        TokenManager.login(user, Boolean.TRUE);
        return resultMap;
    }

    /**
     * 个人资料修改
     * @return
     */
    @RequestMapping(value = "updateSelf", method = RequestMethod.POST)
    public String updateSelf(UUser entity) {
        userService.updateByPrimaryKeySelective(entity);
        return "success";
    }
}
