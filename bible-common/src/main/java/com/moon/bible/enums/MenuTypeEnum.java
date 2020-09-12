
package com.moon.bible.enums;


import com.moon.bible.base.BaseIntEnum;

/**
* @ClassName: MenuTypeEnum 
* @Description: 菜单类型用实体类
* @author caiyang
* @date 2020年6月11日08:26:01
*  
*/
public enum MenuTypeEnum implements BaseIntEnum {

	ADMIN {
		public Integer getCode() {
			return 1;
		}

		public String getName() {
			return "管理后台菜单";
		}
	},
	APPLETS{
		public Integer getCode() {
			return 2;
		}

		public String getName() {
			return "小程序菜单";
		}
	},
}
