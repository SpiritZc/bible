package com.moon.bible.api.sysuser;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysuser.SysUseDto;
import com.moon.bible.dto.sysuser.UserWithRoleDto;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

/**
* @Description: SysUser服务接口
* @author 
* @date 2020-07-14 07:28:39
* @version V1.0  
 */
public interface ISysUserService extends IService<SysUser> , GenericService<SysUser> {

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(SysUser model);

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
	BaseEntity insert(UserWithRoleDto model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(UserWithRoleDto model);
	
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
	 * @Title: changePwd
	 * @Description: 修改密码
	 * @param sysUseDto
	 * @param userInfoDto
	 * @return
	 * @author caiyang
	 * @date 2020-06-12 01:31:09
	 */
	BaseEntity changePwd(SysUseDto sysUseDto, UserInfoDto userInfoDto);

	/**
	 * @Method tablePagingQueryByAuthority
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 根据用户权限查询用户列表
	 * @Return
	 * @Exception
	 * @Date 2020-6-10 11:35
	 */
    BaseEntity tablePagingQueryByAuthority(SysUser model);

    /**
     * @Method resetPwd
     * @Author zhangcheng
     * @Version  1.0
     * @Description 重置密码
     * @Return
     * @Exception
     * @Date 2020-7-24 10:19
     */
	BaseEntity resetPwd(Long id);

	/**
	 * @Method updateStatus
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 启用停用
	 * @Return
	 * @Exception
	 * @Date 2020-7-24 10:19
	 */
	BaseEntity updateStatus(SysUser sysUser);

	/**
	    * 功能描述: 根据角色id查询用户列表<br>
	    * 〈〉
	    * @Param: [roleId]
	    * @Return: java.util.List<com.moon.bible.entity.sysuser.SysUser>
	    * @Author: Administrator
	    * @Date: 2020/8/29 21:13
	 */
	List<SysUser> getUserByRoleId(String roleId);
}
