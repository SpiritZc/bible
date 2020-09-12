package com.moon.bible.dto.sysmenu;

import com.moon.bible.entity.sysmenu.SysMenu;
import lombok.Data;

/**  
 * @ClassName: SysMenuDto
 * @Description: 菜单扩展用dto
 * @author caiyang
 * @date 2020-06-10 05:27:32 
*/  
@Data
public class SysMenuDto extends SysMenu {

	/**  
	 * @Fields isAdmin : 是否超级用户
	 * @author caiyang
	 * @date 2020-06-10 05:27:57 
	 */ 
	private Integer isAdmin;
	
	/**  
	 * @Fields roleId : 角色id
	 * @author caiyang
	 * @date 2020-06-11 08:33:23 
	 */ 
	private Long roleId;
	
}
