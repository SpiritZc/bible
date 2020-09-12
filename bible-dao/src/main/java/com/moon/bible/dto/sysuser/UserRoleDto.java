package com.moon.bible.dto.sysuser;

import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.entity.sysuser.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName : UserRoleDto  //类名
 * @Description : 用户和角色插入dto  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-19 15:03  //时间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto extends SysUser {
    //用户角色
    private SysRole sysRole;
}
