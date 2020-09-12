package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/**
    * 功能描述: 登录类型<br>
    * 〈〉
    * @Param:
    * @Return:
    * @Author: Administrator
    * @Date: 2020/8/31 21:15
 */
public enum LoginTypeEnum implements BaseIntEnum {
    ADMIN {
        public Integer getCode() {
            return 1;
        }

        public String getName() {
            return "后台登录";
        }
    },
    APPLETS{
        public Integer getCode() {
            return 2;
        }

        public String getName() {
            return "小程序端登录";
        }
    },
}
