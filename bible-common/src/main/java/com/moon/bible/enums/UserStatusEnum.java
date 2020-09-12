
package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/** 
* @ClassName: UserStatusEnum 
* @Description: 用户状态枚举类
* @author caiyang
* @date 2020年5月13日17:07:02
*  
*/
public enum UserStatusEnum implements BaseIntEnum {

	YES {
		public Integer getCode() {
			return 1;
		}

		public String getName() {
			return "启用";
		}
	},
	NO {
		public Integer getCode() {
			return 2;
		}

		public String getName() {
			return "停用";
		}
	},
	LOCK{
		public Integer getCode() {
			return 3;
		}

		public String getName() {
			return "锁住";
		}
	},
}
