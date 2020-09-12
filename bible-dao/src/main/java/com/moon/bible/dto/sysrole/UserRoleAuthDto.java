package com.moon.bible.dto.sysrole;

import com.moon.bible.entity.sysrole.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : UserRoleDto  //类名
 * @Description : 授权用dto  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-25 18:15  //时间
 */
@Data
public class UserRoleAuthDto extends SysRole {
    /**
     * @Fields authed : 角色授权的id
     * @author caiyang
     * @date 2020-06-11 11:02:02
     */
    private List<String> authed;
}
