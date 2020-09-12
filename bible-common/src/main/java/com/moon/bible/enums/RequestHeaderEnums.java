package com.moon.bible.enums;

import com.moon.bible.base.BaseCharEnum;

/**  
 * @ClassName: RequestHeaderEnums
 * @Description: 请求头用枚举类
 * @author caiyang
 * @date 2020-06-15 04:49:54 
*/  
public enum RequestHeaderEnums implements BaseCharEnum {

	Authorization {
		public String getCode() {
			return "Authorization";
		}

		public String getName() {
			return "授权";
		}
	},
	ReqSource {
		public String getCode() {
			return "reqSource";
		}

		public String getName() {
			return "请求类型";
		}
	},
	
}
