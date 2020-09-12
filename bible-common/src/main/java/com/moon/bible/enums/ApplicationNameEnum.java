package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;
import com.moon.bible.base.BaseStringEnum;

public enum ApplicationNameEnum implements BaseStringEnum {
    BIBLEADMIN {
        public String getCode() {
            return "bible-admin";
        }

        public String getName() {
            return "上架";
        }
    },
    BIBLEAPPLETS {
        public String getCode() {
            return "bible-applets";
        }

        public String getName() {
            return "下架";
        }
    }
}
