package com.moon.bible.base;

import com.moon.bible.constants.StatusCode;
import com.moon.bible.enums.MsgLevelEnum;
import lombok.Data;

/**
 * 返回数据统一数据对象
 * @author caiyang
 * @version 1.0
 * @date 2020年5月29日10:35:22
 */
@Data
public class ResponseMessage {

	
	/**  
	 * @Fields statusCode : 请求状态码，默认成功码200
	 * @author caiyang
	 * @date 2020-05-29 10:39:12 
	 */ 
	private String code = StatusCode.SUCCESS;
	

	/**
	 * 提示信息
	 */
	/**
	 * 提示信息
	 */
	/**
	 * 提示信息
	 */
	private String message;
	
	/**
	 * 消息类型,默认是成功消息类型
	 */
	private String msgLevel = MsgLevelEnum.SUCCESS.getCode();
	
	
	/**
	 * 返回业务数据
	 */
	private Object responseData;
	
	/**  
	 * @Fields newToken : 新生成的token
	 * @author caiyang
	 * @date 2020-06-08 05:54:44 
	 */ 
	private String newToken;


}