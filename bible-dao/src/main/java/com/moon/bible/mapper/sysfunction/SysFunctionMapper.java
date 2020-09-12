package com.moon.bible.mapper.sysfunction;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.dto.sysmenu.MenuTreeDto;
import com.moon.bible.dto.sysmenu.SysMenuDto;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description: SysFunctionMapper类
* @author 
* @date 2020-07-22 08:57:09
* @version V1.0  
 */
public interface SysFunctionMapper extends BaseMapper<SysFunction> ,GenericMapper<SysFunction>{

    /**
     * @Title: getFunctionsByRole
     * @Description: 根据角色获取功能
     * @param roleId
     * @return
     * @author caiyang
     * @date 2020-06-05 05:53:04
     */
    List<SysFunction> getFunctionsByRole(Long roleId);

    List<MenuTreeDto> getMenuFunctions(SysMenuDto sysMenuDto);
}
