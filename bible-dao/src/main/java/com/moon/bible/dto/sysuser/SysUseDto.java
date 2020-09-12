package com.moon.bible.dto.sysuser;

import com.moon.bible.entity.sysuser.SysUser;
import lombok.Data;

/**
 * @ClassName : SysUseDto  //类名
 * @Description : 修改密码DTO  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-22 16:48  //时间
 */
@Data
public class SysUseDto extends SysUser {
    /**
     * @Fields oldPwd : 旧密码
     * @author caiyang
     * @date 2020-06-12 01:30:02
     */
    private String oldPwd;

    /**
     * @Fields newPwd : 新秘密
     * @author caiyang
     * @date 2020-06-12 01:30:08
     */
    private String newPwd;
}
