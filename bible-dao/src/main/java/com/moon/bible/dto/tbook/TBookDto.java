package com.moon.bible.dto.tbook;

import com.moon.bible.base.UploadResDto;
import com.moon.bible.entity.tbook.TBook;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.dto.tbbok
 * @ClassName: tBookDto
 * @Author: Administrator
 * @Description: 书籍信息上传dto
 * @Date: 2020-7-29 15:57
 * @Version: 1.0
 */
@Data
public class TBookDto extends TBook {
    //图片路径集合
    private List<UploadResDto> imageList;
    //书路径集合
    private List<UploadResDto> urlList;
}
