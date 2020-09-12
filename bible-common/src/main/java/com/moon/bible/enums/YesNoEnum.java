
package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/** 
* @ClassName: YesNoEnum 
* @Description: 是否枚举类
* @author caiyang
* @date 2020年5月13日17:07:02
*  
*/
public enum YesNoEnum implements BaseIntEnum {

	YES {
		public Integer getCode() {
			return 1;
		}

		public String getName() {
			return "是";
		}
	},
	NO {
		public Integer getCode() {
			return 2;
		}

		public String getName() {
			return "否";
		}
	}
}
