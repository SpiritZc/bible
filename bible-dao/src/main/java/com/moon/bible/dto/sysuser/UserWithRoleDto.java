package com.moon.bible.dto.sysuser;

import com.moon.bible.entity.sysuser.SysUser;
import lombok.Data;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.dto.sysuser
 * @ClassName: UserWithRoleDto
 * @Author: Administrator
 * @Description: 用户关联角色
 * @Date: 2020-7-23 9:12
 * @Version: 1.0
 */
@Data
public class UserWithRoleDto extends SysUser {
    //角色id
    private Long roleId;
    //角色名
    private String roleName;
}
