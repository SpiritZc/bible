package com.moon.bible.enums;

import com.moon.bible.base.BaseIntEnum;

/**
 * @ProjectName: bible
 * @Package: com.moon.bible.enums
 * @ClassName: OperateFileStausEnum
 * @Author: Administrator
 * @Description: 操作文件的状态
 * @Date: 2020-7-31 10:45
 * @Version: 1.0
 */
public enum OperateFileStausEnum implements BaseIntEnum {

    UPSUCCESS {
        public Integer getCode() {
            return 1;
        }

        public String getName() {
            return "上传成功";
        }
    },
    UPFAILURE{
        public Integer getCode() {
            return 2;
        }

        public String getName() {
            return "上传失败";
        }
    },
    DELETESUCCESS{
        public Integer getCode() {
            return 3;
        }

        public String getName() {
            return "删除成功";
        }
    },
    DELETEFAILURE{
        public Integer getCode() {
            return 4;
        }

        public String getName() {
            return "删除失败";
        }
    },
    SEARCHSUCCESS{
        public Integer getCode() {
            return 5;
        }

        public String getName() {
            return "查询成功";
        }
    },
    SEARCHFAILURE{
        public Integer getCode() {
            return 6;
        }

        public String getName() {
            return "查询失败";
        }
    }
}
