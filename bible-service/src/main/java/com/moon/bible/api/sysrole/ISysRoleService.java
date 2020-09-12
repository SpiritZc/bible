package com.moon.bible.api.sysrole;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.dto.sysrole.UserRoleAuthDto;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

/**
* @Description: SysRole服务接口
* @author 
* @date 2020-07-14 07:28:29
* @version V1.0  
 */
public interface ISysRoleService extends IService<SysRole> , GenericService<SysRole> {

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(SysRole model);

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
	BaseEntity insert(SysRole model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(SysRole model);
	
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
	    * 功能描述:根据用户id查询角色id <br>
	    * 〈〉
	    * @Param: [id]
	    * @Return: UserRole
	    * @Author: Administrator
	    * @Date: 2020/7/18 18:35
	 */
    SysRole getUserRole(Long id);

    /**
        * 功能描述: 获取角色列表<br>
        * 〈〉
        * @Param: []
        * @Return: com.moon.bible.base.BaseEntity
        * @Author: Administrator
        * @Date: 2020/7/23 21:18
     */
	List<SysRole> getRoles();

    BaseEntity authed(UserRoleAuthDto userRoleDto);

}
