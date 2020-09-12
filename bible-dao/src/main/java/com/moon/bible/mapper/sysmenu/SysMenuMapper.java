package com.moon.bible.mapper.sysmenu;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.dto.sysmenu.IndexMenuTreeDto;
import com.moon.bible.dto.sysmenu.MenuTreeDto;
import com.moon.bible.dto.sysmenu.SysMenuDto;
import com.moon.bible.entity.sysmenu.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description: SysMenuMapper类
* @author 
* @date 2020-07-22 08:57:16
* @version V1.0  
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> ,GenericMapper<SysMenu>{

    /**
     * @Title: getIndexMenu
     * @Description: 获取首页菜单树
     * @param sysMenuDto
     * @return
     * @author caiyang
     * @date 2020-06-11 05:35:49
     */
    List<IndexMenuTreeDto> getIndexMenu(SysMenuDto sysMenuDto);

    /**
     * @Title: getMenuTree
     * @Description: 获取授权菜单树
     * @param sysMenuDto
     * @return
     * @author caiyang
     * @date 2020-06-11 08:32:21
     */
    List<MenuTreeDto> getMenuTree(SysMenuDto sysMenuDto);
}
