package com.moon.bible.api.sysmenu;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysmenu.IndexMenuTreeDto;
import com.moon.bible.entity.sysmenu.SysMenu;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;
import com.moon.bible.entity.sysrole.SysRole;

/**
* @Description: SysMenu服务接口
* @author 
* @date 2020-07-22 08:57:16
* @version V1.0  
 */
public interface ISysMenuService extends IService<SysMenu> ,GenericService<SysMenu>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(SysMenu model);

	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author caiyang
	* @param id
	* @return
	*/
	BaseEntity getDetail(Long id);
	
	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity insert(SysMenu model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(SysMenu model);
	
	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity delete(Long id);
	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param list
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);

	 /**
	  * @Title: getIndexMenu
	  * @Description: 获取首页菜单树
	  * @param userInfoDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-11 05:14:23
	  */
	 List<IndexMenuTreeDto> getIndexMenu(UserInfoDto userInfoDto);

	/**
	 * @Title: getAuthTree
	 * @Description: 获取菜单授权树
	 * @param sysRole,userInfoDto
	 * @return
	 * @author caiyang
	 * @date 2020-06-10 05:04:40
	 */
	BaseEntity getAuthTree(SysRole sysRole, UserInfoDto userInfoDto);

	/**
	 * @Title: getAllMenus
	 * @Description: 获取所有的后台菜单
	 * @return
	 * @author caiyang
	 * @date 2020-06-16 01:20:03
	 */
	List<SysMenu> getAllMenus();
}
