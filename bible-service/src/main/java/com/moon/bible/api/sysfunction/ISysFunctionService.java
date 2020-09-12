package com.moon.bible.api.sysfunction;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.dto.sysmenu.MenuTreeDto;
import com.moon.bible.dto.sysmenu.SysMenuDto;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: SysFunction服务接口
* @author 
* @date 2020-07-22 08:57:09
* @version V1.0  
 */
public interface ISysFunctionService extends IService<SysFunction> ,GenericService<SysFunction>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(SysFunction model);

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
	BaseEntity insert(SysFunction model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(SysFunction model);
	
	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author caiyang
	* @param id
	* @return
	*/
	BaseEntity delete(Long id);
	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param ids
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);

	 /**
	  * @Title: getAllFunctions
	  * @Description: 获取全部的功能
	  * @return
	  * @author caiyang
	  * @date 2020-06-08 08:54:42
	  */
	 List<SysFunction> getAllFunctions();

	 /**
	  * @Title: getAllFunctionsByType
	  * @Description: 根据类型获取全部的功能
	  * @return
	  * @author caiyang
	  * @date 2020-06-08 08:54:42
	  */
	 List<SysFunction> getAllFunctionsByType(Integer type);

	 /**
	  * @Title: getFunctionsByRole
	  * @Description: 根据角色获取功能
	  * @param roleId
	  * @return
	  * @author caiyang
	  * @date 2020-06-05 05:51:41
	  */
	 List<SysFunction> getFunctionsByRole(Long roleId);


	 /**
	  * @Title: updatePermission
	  * @Description: 动态更新权限，不需要重启服务
	  * @return
	  * @author caiyang
	  * @date 2020-06-12 03:25:11
	  */
	 BaseEntity updatePermission();

     List<MenuTreeDto> getMenuFunctions(SysMenuDto sysMenuDto);
 }
