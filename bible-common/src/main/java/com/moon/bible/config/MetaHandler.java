package com.moon.bible.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.enums.RequestHeaderEnums;
import com.moon.bible.util.JWTUtil;
import com.moon.bible.util.StringUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**  
 * @ClassName: MetaHandler
 * @Description: 公共字段写入
 * @author caiyang
 * @date 2020-05-29 10:30:23 
*/  
@Component
public class MetaHandler implements MetaObjectHandler{

	@Autowired
	private HttpServletRequest httpServletRequest;
	
	/**  
	 * @Title: insertFill
	 * @Description: 新增时写入创建时间和更新时间
	 * @param metaObject 
	 * @see MetaObjectHandler#insertFill(MetaObject)
	 * @author caiyang
	 * @date 2020-05-29 10:30:36
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		//如果有create_time字段，则自动填充
		if (metaObject.hasGetter("createTime")) {
			this.setFieldValByName("createTime", new Date(), metaObject);
		}
		//如果有creator或者creatorName字段，自动填充
		if (metaObject.hasGetter("creator") || metaObject.hasGetter("creatorName")) {
			UserInfoDto userInfoDto = new UserInfoDto();
			String authorization = httpServletRequest.getHeader(RequestHeaderEnums.Authorization.getCode());//获取header信息
			if (!StringUtil.isNullOrEmpty(authorization)) {
				userInfoDto = JWTUtil.getUserInfo(authorization);
					if (metaObject.hasGetter("creator") && userInfoDto != null) {
					this.setFieldValByName("creator", userInfoDto.getUserId(), metaObject);
				}
				if (metaObject.hasGetter("creatorName") && userInfoDto != null) {
					this.setFieldValByName("creatorName", userInfoDto.getUserName(), metaObject);
				}
			}
		}
	}

	/**
	 * @Title: updateFill
	 * @Description: 更新是写入更新时间
	 * @param metaObject
	 * @see MetaObjectHandler#updateFill(MetaObject)
	 * @author caiyang
	 * @date 2020-05-29 10:30:58 
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
//		 this.setFieldValByName("updateTime", new Date(), metaObject);
	}

}
