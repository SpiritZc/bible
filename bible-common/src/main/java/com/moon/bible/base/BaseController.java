package com.moon.bible.base;

import com.moon.bible.constants.StatusCode;
import com.moon.bible.enums.MsgLevelEnum;
import com.moon.bible.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName:  BaseController   
 * @Description:基底controller，controller统一处理 
 * @author: caiyang 
 * @date:   2020年5月29日17:23:53
 *      
 */
public class BaseController {

	@Autowired
	protected HttpServletRequest httpServletRequest;
	@Autowired
	protected HttpServletResponse httpServletResponse;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**   
	 * @Title: exception   
	 * @Description: 异常统一处理 
	 * @param: @param
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	@ExceptionHandler
	public Object exception(Exception e)
	{
		String currentUrl = httpServletRequest.getServletPath();//当前请求的url
		logger.error("请求" + currentUrl + "调用结束，调用过程出现异常，异常类型"+e.getClass().toString()+"，异常内容：", e);
		BaseEntity baseEntity = new BaseEntity();
		//自定义异常抛出
		if (e instanceof BizException)
		{
			BizException bizException = (BizException)e;
			baseEntity.setStatusCode(bizException.getCode());
			baseEntity.setStatusMsg(bizException.getMessage());
			baseEntity.setMsgLevel(MsgLevelEnum.ERROR.getCode());
		}else {
			baseEntity.setStatusCode(StatusCode.FAILURE);
			baseEntity.setStatusMsg("系统异常，请与管理员联系。");
			baseEntity.setMsgLevel(MsgLevelEnum.ERROR.getCode());
		}
		return baseEntity;
	}

}
