package com.moon.bible.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.base.UserInfoDto;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: plat-aggregation
 * @Package: com.baiyyy.yfz.util
 * @ClassName: JWTUtil
 * @Author: caiyang
 * @Description: jwt工具类
 * @Date: 2020-6-4 10:01
 * @Version: 1.0
 */
public class JWTUtil {

	/**  
	 * @Fields EXPRIE_TIME : pc端运营后台token过期时间2小时
	 * @author caiyang
	 * @date 2020-06-08 08:05:30 
	 */ 
	public static final long PC_ADMIN_EXPRIE_TIME = 24*60*60*1000;

	/**  
	 * @Fields PC_REFRESH_TIME : pc端运营后台token过期刷新时间2小时,过期两个小时以内允许继续访问并重新生成token
	 * @author caiyang
	 * @date 2020-06-08 08:08:48 
	 */ 
	public static final long PC_ADMIN_REFRESH_TIME = 24*60*60*1000;
	
    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static String verify(String token, UserInfoDto userInfoDto, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("accountName", userInfoDto.getAccountName())
                    .withClaim("userName", userInfoDto.getUserName())
                    .withClaim("userId", userInfoDto.getUserId())
                    .withClaim("roleId", userInfoDto.getRoleId())
                    .withClaim("roleName", userInfoDto.getRoleName())
                    .withClaim("isAdmin", userInfoDto.getIsAdmin())
                    .withClaim("expireDate", userInfoDto.getExpireDate())
					.withClaim("phoneNo", userInfoDto.getPhoneNo())
//                    .withClaim("authType", userInfoDto.getAuthType())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return StatusCode.SUCCESS;
        } catch (Exception e) {
        	if (e instanceof TokenExpiredException) {
        		return StatusCode.TOKEN_FAILURE;//token过期失效
        	}else {
        		return StatusCode.TOKEN_ERROR;//token错误
        	}
        }
    }


    /**  
     * @Title: sign
     * @Description: 生成token
     * @param userInfoDto
     * @param secret
     * @return
     * @author caiyang
     * @date 2020-06-08 07:59:03 
     */ 
    public static String sign(UserInfoDto userInfoDto,String secret)
    {
    	Date date = new Date(System.currentTimeMillis()+PC_ADMIN_EXPRIE_TIME);
    	Algorithm algorithm = Algorithm.HMAC512(secret);
    	// 附带username信息
    	return JWT.create()
    		    .withClaim("accountName", userInfoDto.getAccountName())//账户名
    			.withClaim("userName", userInfoDto.getUserName())//用户名
    			.withClaim("userId", userInfoDto.getUserId())//用户id
    			.withClaim("roleId", userInfoDto.getRoleId())//角色id
    			.withClaim("roleName", userInfoDto.getRoleName())//角色名称
    			.withClaim("isAdmin", userInfoDto.getIsAdmin())//是否超级管理员
//    			.withClaim("orgIds", userInfoDto.getOrgIds())//所属组织
    			.withClaim("expireDate", date)//过期时间
				.withClaim("phoneNo", userInfoDto.getPhoneNo())//过期时间
//    			.withClaim("authType", userInfoDto.getAuthType())//授权类型
    			.withExpiresAt(date)
    			.sign(algorithm);
    }
    
    /**  
     * @Title: getUserInfo
     * @Description: 获取用户信息
     * @param token
     * @return
     * @author caiyang
     * @date 2020-06-08 01:13:13 
     */ 
    public static UserInfoDto getUserInfo(String token)
    {
    	try {
			UserInfoDto userInfoDto = new UserInfoDto();
			DecodedJWT jwt = JWT.decode(token);
			String accountName = jwt.getClaim("accountName").asString();
			userInfoDto.setAccountName(accountName);
			String userName = jwt.getClaim("userName").asString();
			userInfoDto.setUserName(userName);
			Long userId = jwt.getClaim("userId").asLong();
			userInfoDto.setUserId(userId);
			Long roleId = jwt.getClaim("roleId").asLong();
			userInfoDto.setRoleId(roleId);
			String roleName = jwt.getClaim("roleName").asString();
			userInfoDto.setRoleName(roleName);
			Integer isAdmin = jwt.getClaim("isAdmin").asInt();
			userInfoDto.setIsAdmin(isAdmin);
			String phoneNo = jwt.getClaim("phoneNo").asString();
			userInfoDto.setPhoneNo(phoneNo);
//			List<Long> orgIds = jwt.getClaim("orgIds").asList(Long.class);
//			userInfoDto.setOrgIds(orgIds);
			Date expireDate = jwt.getClaim("expireDate").asDate();
			userInfoDto.setExpireDate(expireDate);
			return userInfoDto;
		} catch (Exception e) {
//    		e.printStackTrace();
			return null;
		}
    }
}

