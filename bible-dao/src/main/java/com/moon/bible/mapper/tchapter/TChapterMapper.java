package com.moon.bible.mapper.tchapter;
import com.moon.bible.base.GenericMapper;
import com.moon.bible.entity.tchapter.TChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

 /**  
* @Description: TChapterMapper类
* @author 
* @date 2020-08-22 08:31:37
* @version V1.0  
 */
public interface TChapterMapper extends BaseMapper<TChapter> ,GenericMapper<TChapter>{

     void deletOldChapter(Long id);
 }
