package com.moon.bible.base;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**  
 * @ClassName: UserInfoDto
 * @Description: 用户信息用实体类
 * @author caiyang
 * @date 2020-06-05 04:45:16 
*/  
@Data
public class UserInfoDto extends BaseEntity{

	/**  
	 * @Fields token : token信息
	 * @author caiyang
	 * @date 2020-06-05 05:02:24 
	 */ 
	private String token;
	
	/**  
	 * @Fields userName : 用户姓名
	 * @author caiyang
	 * @date 2020-06-05 05:03:03 
	 */ 
	private String userName;

	/**
	 * @Fields phoneNo : 手机号
	 * @author caiyang
	 * @date 2020-06-05 05:03:03
	 */
	private String phoneNo;
	
	/**  
	 * @Fields accountName : 账号名(唯一)
	 * @author caiyang
	 * @date 2020-06-05 05:03:29 
	 */ 
	private String accountName;
	
	/**  
	 * @Fields isAdmin : 是否超级管理员
	 * @author caiyang
	 * @date 2020-06-05 05:05:14 
	 */ 
	private Integer isAdmin;
	
	/**  
	 * @Fields promissionCodes : 角色对应的api权限
	 * @author caiyang
	 * @date 2020-06-05 05:05:32 
	 */
	private List<String> functions;
	
	/**  
	 * @Fields roleId : 角色id
	 * @author caiyang
	 * @date 2020-06-05 05:06:00 
	 */ 
	private Long roleId;
	
	/**  
	 * @Fields roleName : 角色名称
	 * @author caiyang
	 * @date 2020-06-05 05:06:17 
	 */ 
	private String roleName;
	
	/**  
	 * @Fields roleCode : 角色代码
	 * @author caiyang
	 * @date 2020-06-05 05:25:50 
	 */ 
	private String roleCode;
	
	/**  
	 * @Fields userId : 用户id
	 * @author caiyang
	 * @date 2020-06-05 05:06:33 
	 */ 
	private Long userId;
	
	/**  
	 * @Fields orgIds : 用户所属的组织
	 * @author caiyang
	 * @date 2020-06-05 05:06:50 
	 */ 
	private List<Long> orgIds;
	
	/**  
	 * @Fields ExpireDate : 过期时间
	 * @author caiyang
	 * @date 2020-06-08 08:16:16 
	 */ 
	private Date expireDate;

}
