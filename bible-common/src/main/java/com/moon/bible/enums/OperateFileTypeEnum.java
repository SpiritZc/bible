package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/**
 * @ProjectName: bible
 * @Package: com.moon.bible.enums
 * @ClassName: OperateFileTypeEnum
 * @Author: Administrator
 * @Description: 文件操作类型
 * @Date: 2020-7-31 11:30
 * @Version: 1.0
 */
public enum OperateFileTypeEnum implements BaseIntEnum {
    UPLOAD {
        public Integer getCode() {
            return 1;
        }

        public String getName() {
            return "上传";
        }
    },
    DOWN{
        public Integer getCode() {
            return 2;
        }

        public String getName() {
            return "下载";
        }
    },
    DELETE{
        public Integer getCode() {
            return 3;
        }

        public String getName() {
            return "删除";
        }
    },
    SEARCH{
        public Integer getCode() {
            return 4;
        }

        public String getName() {
            return "查询";
        }
    }
}
