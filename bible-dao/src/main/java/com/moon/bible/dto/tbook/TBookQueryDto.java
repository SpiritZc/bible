package com.moon.bible.dto.tbook;

import com.moon.bible.entity.tbook.TBook;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: bible
 * @Package: com.moon.bible.dto.tbbok
 * @ClassName: TBookQueryDto
 * @Author: Administrator
 * @Description: 查询带日期dto
 * @Date: 2020-7-31 16:08
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class TBookQueryDto extends TBook {
    //查询开始时间
    private String startDate;
    //查询结束时间
    private String endDate;
}
