package com.moon.bible.constants;


/**
 * @Description: 系统常量类
 * @author 蔡阳
 * @date 2020年5月29日10:28:46
 * @version V1.0
 */
public class Constants {
	
	
	/**
	 * 操作类型，用于操作日志时存储
	 */
	public static final String OPERATE_TYPE_ADD = "Add";
	public static final String OPERATE_TYPE_UPDATE = "Update";
	public static final String OPERATE_TYPE_DELETE = "Delete";
	public static final String OPERATE_TYPE_BATCHDELETE = "BatchDelete";
	public static final String OPERATE_TYPE_SEARCH = "Search";
	public static final String OPERATE_TYPE_LOGIN = "Login";
	public static final String OPERATE_TYPE_LOGOUT = "Logout";
	
	/**  
	 * @Fields DEFAULT_PASSWORD : 默认密码
	 * @author caiyang
	 * @date 2020-06-12 04:18:48 
	 */ 
	public static final String DEFAULT_PASSWORD = "123456";
	
    /**  
     * @Fields PC_ADMIN_PREMISSION_STRING : pc端运营后台授权访问字符串  
     * @author caiyang
     * @date 2020-06-12 04:19:05 
     */ 
    public static final String  PC_ADMIN_PREMISSION_STRING = "jwt,perms[{0}]";  
    
    /**  
     * @Fields PUBLIC_STRING : 公开访问权限字符串  
     * @author caiyang
     * @date 2020-06-12 04:19:12 
     */ 
    public static final String  PUBLIC_STRING = "anon";
    
    /**  
     * @Fields JWT_STRING : PC端运营后台登陆访问字符串  
     * @author caiyang
     * @date 2020-06-12 04:19:20 
     */ 
    public static final String  JWT_STRING = "jwt";
    
    /**  
     * @Fields JWT_MOBILE_STRING : 移动端登录访问字符串，包括app和小程序
     * @author caiyang
     * @date 2020-06-08 08:53:23 
     */ 
    public static final String JWT_APPLETS_STRING = "jwt_applets";

	/**
	 * @Fields JWT_MOBILE_STRING : 移动端登录访问字符串，包括app和小程序
	 * @author caiyang
	 * @date 2020-06-08 08:53:23
	 */
	public static final String APPLETS_PREMISSION_STRING = "jwt_applets,perms[{0}]";
    
    /**  
     * @Fields LOGIN_TIMES : 登录次数
     * @author caiyang
     * @date 2020-06-15 05:53:39 
     */ 
    public static final Integer LOGIN_TIMES = 3;
    
    /**  
     * @Fields LOGIN_TIME_INTERVAL : 登录出入密码次数时间，10分钟内三次错误则锁定五分钟
     * @author caiyang
     * @date 2020-06-16 07:48:21 
     */ 
    public static final Long LOGIN_TIME_INTERVAL = 10 * 60 * 1000L;
    
    /**  
     * @Fields LOGIN_TIME_LOCK : 登录锁定时间五分钟
     * @author caiyang
     * @date 2020-06-16 10:04:04 
     */ 
    public static final Long LOGIN_TIME_LOCK = 5 * 60 * 1000L;
}
