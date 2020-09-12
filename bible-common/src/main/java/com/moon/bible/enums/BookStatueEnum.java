package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

public enum BookStatueEnum implements BaseIntEnum {
    PUT {
        public Integer getCode() {
            return 1;
        }

        public String getName() {
            return "上架";
        }
    },
    OFF {
        public Integer getCode() {
            return 2;
        }

        public String getName() {
            return "下架";
        }
    }
}
