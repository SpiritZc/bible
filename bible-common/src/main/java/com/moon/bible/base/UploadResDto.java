package com.moon.bible.base;

import com.moon.bible.base.BaseEntity;
import lombok.Data;

/**
 * @ClassName : uploadResDto  //类名
 * @Description : 上传返回dto  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-28 22:10  //时间
 */
@Data
public class UploadResDto extends BaseEntity {
    //上传成功后的url
    private String url;

    //上传时候新生成的文件名
    private String newName;
}
