package com.moon.bible.mapper.sysrole;
import com.moon.bible.entity.sysrole.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bible.base.GenericMapper;
import org.apache.ibatis.annotations.Param;

/**
* @Description: SysRoleMapper类
* @author 
* @date 2020-07-14 07:28:29
* @version V1.0  
 */
public interface SysRoleMapper extends BaseMapper<SysRole> , GenericMapper<SysRole> {

    /**
        * 功能描述: <br>
        * 〈〉
        * @Param: [id]
        * @Return: com.moon.bible.entity.sysrole.SysRole
        * @Author: Administrator
        * @Date: 2020/7/18 18:38
     */
    SysRole getUserRole(@Param(value = "id") Long id);
}
