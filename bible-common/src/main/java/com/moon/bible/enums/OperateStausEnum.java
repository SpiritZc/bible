
package com.moon.bible.enums;


import com.moon.bible.base.BaseIntEnum;

/**
* @ClassName: OperateStausEnum 
* @Description: 操作状态枚举类
* @author caiyang
* @date 2020年5月29日09:59:20
*  
*/
public enum OperateStausEnum implements BaseIntEnum {

	SUCCESS {
		public Integer getCode() {
			return 1;
		}

		public String getName() {
			return "成功";
		}
	},
	FAILURE{
		public Integer getCode() {
			return 2;
		}

		public String getName() {
			return "失败";
		}
	}
}
