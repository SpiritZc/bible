package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/**  
 * @ClassName: FunctionRuleEnum
 * @Description: 功能访问权限规则枚举类
 * @author caiyang
 * @date 2020-06-08 09:00:22 
*/  
public enum FunctionRuleEnum implements BaseIntEnum {

	PUB {
		public Integer getCode() {
			return 1;
		}

		public String getName() {
			return "公开访问";
		}
	},
	JWT {
		public Integer getCode() {
			return 2;
		}

		public String getName() {
			return "登陆后访问";
		}
	},
	JWTAUTH {
		public Integer getCode() {
			return 3;
		}

		public String getName() {
			return "登陆后并授权访问";
		}
	}
}
