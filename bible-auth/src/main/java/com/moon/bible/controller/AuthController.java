package com.moon.bible.controller;

import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.enums.MsgLevelEnum;
import com.moon.bible.util.MessageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**  
 * @ClassName: AuthController
 * @Description: shiro权限用controller
 * @author caiyang
 * @date 2020-06-12 09:28:24 
*/ 
@RestController
@RequestMapping("/api")
public class AuthController extends BaseController {

	@RequestMapping(value = "/unnauthorized",method = RequestMethod.GET)
	public BaseEntity unauthorized() {
		BaseEntity result = new BaseEntity();
		result.setStatusCode(StatusCode.AUTH_FAILURE);
		result.setStatusMsg(MessageUtil.getValue("error.auth"));
		result.setMsgLevel(MsgLevelEnum.ERROR.getCode());
		return result;
	}
}
