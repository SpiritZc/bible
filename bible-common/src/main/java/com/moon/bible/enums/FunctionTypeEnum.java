package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

public enum FunctionTypeEnum implements BaseIntEnum {
    ADMIN {
        public Integer getCode() {
            return 1;
        }

        public String getName() {
            return "管理后台功能";
        }
    },
    APPLETS{
        public Integer getCode() {
            return 2;
        }

        public String getName() {
            return "小程序功能";
        }
    },
}
