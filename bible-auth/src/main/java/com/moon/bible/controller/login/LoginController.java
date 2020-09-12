package com.moon.bible.controller.login;

import com.moon.bible.annotation.Check;
import com.moon.bible.api.login.ILoginService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.dto.sysuser.UserLoginDto;
import com.moon.bible.entity.sysuser.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.controller.login
 * @ClassName: LoginController
 * @Author: Administrator
 * @Description: 登录controller
 * @Date: 2020-7-16 11:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

    @Autowired
    private ILoginService iLoginService;

    /**
     * @Title: doLogin
     * @Description: 用户登录
     * @param sysUser
     * @return
     * @author zhangcheng
     * @date 2020-06-12 01:20:05
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
//    @Check({"accountName:required#用户名","password:required#密码"})
    public BaseEntity doLogin(@RequestBody UserLoginDto sysUser) throws Exception {
        BaseEntity result = iLoginService.doLogin(sysUser);
        return result;
    }

}
