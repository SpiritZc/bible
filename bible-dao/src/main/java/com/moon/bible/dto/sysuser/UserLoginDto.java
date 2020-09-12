package com.moon.bible.dto.sysuser;

import com.moon.bible.entity.sysuser.SysUser;
import lombok.Data;

/**
 * @ClassName : UserLoginDto  //类名
 * @Description : 用户登录DTO  //描述
 * @Author : HTB  //作者
 * @Date: 2020-08-31 21:10  //时间
 */
@Data
public class UserLoginDto extends SysUser {
    //登录类型 1运营后台2微信小程序
    private Integer loginType;

    //微信端jscode
    private String jscode;

    //包括敏感数据在内的完整用户信息的加密数据
    private String encryptedData;

    //加密算法的初始向量
    private String iv;
}
