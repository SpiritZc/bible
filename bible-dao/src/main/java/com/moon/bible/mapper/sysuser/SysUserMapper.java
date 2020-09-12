package com.moon.bible.mapper.sysuser;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.dto.sysuser.UserWithRoleDto;
import com.moon.bible.entity.sysuser.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bible.base.GenericMapper;

import java.util.List;

/**
* @Description: SysUserMapper类
* @author 
* @date 2020-07-14 07:28:39
* @version V1.0  
 */
public interface SysUserMapper extends BaseMapper<SysUser> , GenericMapper<SysUser> {
    /**
     * @Method tablePagingQueryByAuthority
     * @Author zhangcheng
     * @Version  1.0
     * @Description 根据用户权限查询用户列表
     * @Return com.baiyyy.yfz.base.BaseEntity
     * @Exception
     * @Date 2020-6-10 11:36
     */
    List<UserWithRoleDto> tablePagingQueryByAuthority(SysUser model);

    BaseEntity getDetail(Long id);

    /**
        * 功能描述: 根据角色id查询用户列表<br>
        * 〈〉
        * @Param: [roleId]
        * @Return: java.util.List<com.moon.bible.entity.sysuser.SysUser>
        * @Author: Administrator
        * @Date: 2020/8/29 21:17
     */
    List<SysUser> getUserByRoleId(String roleId);
}
