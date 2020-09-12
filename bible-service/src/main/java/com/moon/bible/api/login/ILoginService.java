package com.moon.bible.api.login;

import com.moon.bible.dto.sysuser.UserLoginDto;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.base.UserInfoDto;

/**
 * @ClassName: ILoginService
 * @Description: 登录用服务接口
 * @author zhangcheng
 * @date 2020-06-05 04:44:35
 */
public interface ILoginService {

    /**
     * @Title: doLogin
     * @Description: 平台用户登录
     * @param sysUser
     * @return
     * @author zhangcheng
     * @date 2020-06-05 04:46:00
     */
    UserInfoDto doLogin(UserLoginDto sysUser) throws Exception;
}
