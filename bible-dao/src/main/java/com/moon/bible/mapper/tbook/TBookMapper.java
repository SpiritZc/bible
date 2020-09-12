package com.moon.bible.mapper.tbook;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.dto.tbook.TBookQueryDto;
import com.moon.bible.entity.tbook.TBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moon.bible.entity.tchapter.TChapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description: TBookMapper类
* @author 
* @date 2020-07-26 05:37:41
* @version V1.0  
 */
public interface TBookMapper extends BaseMapper<TBook> ,GenericMapper<TBook>{

    /**
     * @Method tableQueryByTime
     * @Author zhangcheng
     * @Version  1.0
     * @Description 根据日期查询列表
     * @Return
     * @Exception
     * @Date 2020-7-31 16:24
     */
     List<TBook> tableQueryByTime(TBookQueryDto model);

     /**
         * 功能描述: 批量插入<br>
         * 〈〉
         * @Param: [chapters]
         * @Return: void
         * @Author: Administrator
         * @Date: 2020/8/22 21:33
      */
    void insertList(List<TChapter> chapters);
}
