package com.moon.bible.impl.tbook;

import com.aliyun.oss.model.OSSObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.api.common.IUploadService;
import com.moon.bible.api.tchapter.ITChapterService;
import com.moon.bible.base.OSSDto;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.dto.tbook.TBookQueryDto;
import com.moon.bible.entity.tbook.TBook;
import com.moon.bible.entity.tchapter.TChapter;
import com.moon.bible.enums.BookStatueEnum;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.tbook.TBookMapper;
import com.moon.bible.api.tbook.ITBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bible.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

 /**  
* @Description: TBook服务实现
* @author 
* @date 2020-07-26 05:37:41
* @version V1.0  
 */
@Service
@Slf4j
public class TBookServiceImpl extends ServiceImpl<TBookMapper, TBook> implements ITBookService {

	@Autowired
	private IUploadService iUploadService;

	@Autowired
	private ITChapterService itChapterService;
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(TBook model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<TBook> list = this.baseMapper.searchDataLike(model);
		result.setData(list);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}


	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author 
	* @param id
	* @return
	*/
	@Override
	public BaseEntity getDetail(Long id) {
		return this.getById(id);
	}

	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity insert(TBook model) {
		BaseEntity result = new BaseEntity();
		if (StringUtils.isBlank(model.getImage())){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty",new String[]{"图片"}));
		}
		if (StringUtils.isBlank(model.getUrl())){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty",new String[]{"文件"}));
		}
		//不能书名和出版社都一样
		if (this.checkRepeat(new QueryWrapper<TBook>().eq("book_name", model.getBookName()).eq("publishing",model.getPublishing())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"该出版社这本书"}));
		}
		model.setPinyin(PinYinUtils.toPinyin(model.getBookName()));//拼音
		model.setState(BookStatueEnum.PUT.getCode());//书籍状态 上架
		model.setHits(0);//浏览次数
		this.save(model);
		//书籍文件进行拆分，保存到卷章节表里
		//获取文件的流
		OSSDto file = iUploadService.getFile(model.getUrl());
		String content = file.getContent();
		//插入的集合
		List<TChapter> chapters = new ArrayList<>();
		//获取到所有卷的集合
		Map<String, List<String>> stringListMap = BookSplitUtil.splitBook(content);
		//遍历
		stringListMap.forEach((k,v)->{
			v.forEach(p->{
				TChapter tChapter = new TChapter();
				//如果是卷首 那么章节都算0
				if (!CheckUtil.isNumber(p.substring(0, 1))){
					tChapter.setVolume(k);//卷名
					tChapter.setChapter("0");//章名
					tChapter.setSection("0");//节名
					tChapter.setBookId(model.getId());//小说id
					tChapter.setWordCount(p.length());//字数
					tChapter.setIntro(p);//章节内容
					tChapter.setReadNum(0);//阅读次数
				}else {
					//截取章节
					String chapter = p.substring(0, p.indexOf(":"));//章
					String section = p.substring(p.indexOf(":") + 1, p.indexOf(" "));//节
					tChapter.setVolume(k);
					tChapter.setChapter(chapter);
					tChapter.setSection(section);
					tChapter.setBookId(model.getId());//小说id
					tChapter.setWordCount(p.length());//字数
					tChapter.setIntro(p);//章节内容
					tChapter.setReadNum(0);//阅读次数
				}
				chapters.add(tChapter);
			});
		});
		//批量插入
		itChapterService.saveBatch(chapters);
		result.setStatusMsg(MessageUtil.getValue("info.insert"));
		return result;
	}

	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity update(TBook model) {
		BaseEntity result = new BaseEntity();
		TBook tBook = this.baseMapper.selectById(model.getId());
		if (!StringUtils.isBlank(model.getImage())){
			//判断是否修改了图片
			if (!tBook.getImage().equals(model.getImage())){
				try {
					//删除原图片
					iUploadService.deleteFile(tBook.getImage());
				} catch (Exception e) {
					e.printStackTrace();
					log.info(MessageUtil.getValue("error.file.delete",new String[]{"图片"}));
				}
			}

		}
		if (!StringUtils.isBlank(model.getUrl())){
			//判断是否修改了文件
			if (!tBook.getUrl().equals(model.getUrl())){
				try {
					//删除原文件
					iUploadService.deleteFile(tBook.getUrl());
					//删除原文件对应的章节
					itChapterService.deletOldChapter(tBook.getId());
				} catch (Exception e) {
					e.printStackTrace();
					log.info(StatusCode.FAILURE, MessageUtil.getValue("error.file.delete",new String[]{"文件"}));
				}
			}
		}
		//不能书名和出版社都一样
		if (this.checkRepeat(new QueryWrapper<TBook>().eq("book_name", model.getBookName()).eq("publishing",model.getPublishing()).ne("id",model.getId())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"该出版社这本书"}));
		}
		this.updateById(model);
		//如果修改了文件,就要重新上传文件
		if (!StringUtils.isBlank(model.getUrl())){
			//书籍文件进行拆分，保存到卷章节表里
			//获取文件的流
			OSSDto file = iUploadService.getFile(model.getUrl());
			String content = file.getContent();
			//插入的集合
			List<TChapter> chapters = new ArrayList<>();
			//获取到所有卷的集合
			Map<String, List<String>> stringListMap = BookSplitUtil.splitBook(content);
			//遍历
			stringListMap.forEach((k,v)->{
				v.forEach(p->{
					TChapter tChapter = new TChapter();
					//如果是卷首 那么章节都算0
					if (!CheckUtil.isNumber(p.substring(0, 1))){
						tChapter.setVolume(k);//卷名
						tChapter.setChapter("0");//章名
						tChapter.setSection("0");//节名
						tChapter.setBookId(model.getId());//小说id
						tChapter.setWordCount(p.length());//字数
						tChapter.setIntro(p);//章节内容
						tChapter.setReadNum(0);//阅读次数
					}else {
						//截取章节
						String chapter = p.substring(0, p.indexOf(":"));//章
						String section = p.substring(p.indexOf(":") + 1, p.indexOf(" "));//节
						if (section.length()>5){//有一些节后面没有东西
							if (section.indexOf(",") == -1)
								section = p.trim().substring(p.trim().indexOf(":") + 1);
							else{

							}
						}
						tChapter.setVolume(k);
						tChapter.setChapter(chapter);
						tChapter.setSection(section);
						tChapter.setBookId(model.getId());//小说id
						tChapter.setWordCount(p.length());//字数
						tChapter.setIntro(p);//章节内容
						tChapter.setReadNum(0);//阅读次数
					}
					chapters.add(tChapter);
				});
			});
			chapters.stream().forEach(p->{
				if (p.getSection().length()>=50){
					System.out.println(p.getSection());
				}
			});
			//批量插入
			itChapterService.saveBatch(chapters);
		}
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author 
	* @param id
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		TBook tBook = new TBook();
		tBook.setId(id);
		tBook.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(tBook);
		TBook tBook1 = this.baseMapper.selectById(id);
		//删除图片
		try {
			iUploadService.deleteFile(tBook1.getImage());
		} catch (Exception e) {
			e.printStackTrace();
			log.info(MessageUtil.getValue("error.file.delete",new String[]{"图片"}));
		}
		//删除文件
		try {
			iUploadService.deleteFile(tBook1.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
			log.info(MessageUtil.getValue("error.file.delete",new String[]{"文件"}));
		}
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author 
	* @param list
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<TBook> list = new ArrayList<TBook>();
		for (int i = 0; i < ids.size(); i++) {
			TBook tBook = new TBook();
			tBook.setId(ids.get(i));
			tBook.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(tBook);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		//删除文件
		list.forEach(p->{
			//删除图片
			try {
				iUploadService.deleteFile(p.getImage());
			} catch (Exception e) {
				e.printStackTrace();
				log.info(MessageUtil.getValue("error.file.delete",new String[]{"图片"}));
			}
			//删除文件
			try {
				iUploadService.deleteFile(p.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
				log.info(MessageUtil.getValue("error.file.delete",new String[]{"文件"}));
			}
		});
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	 /**
	 * @Method updateStatus
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 修改书籍状态
	 * @Return
	 * @Exception
	 * @Date 2020-7-31 16:22
	 */
	 @Override
	 @Transactional
	 public BaseEntity updateStatus(TBook tBook) {
		 BaseEntity result = new BaseEntity();
		 TBook tBookn = new TBook();
		 tBookn.setId(tBook.getId());
		 tBookn.setState(tBook.getState());
		 this.updateById(tBookn);
		 result.setStatusMsg(MessageUtil.getValue("info.operation"));
		 return result;
	 }

	 /**
	  * @Title: tablePagingQueryByTime
	  * @Description: 表格分页查询根据时间
	  * @param @param model
	  * @return BaseEntity
	  * @author
	  * @throws
	  */
	 @Override
	 public PageEntity tablePagingQueryByTime(TBookQueryDto model) {
		 PageEntity result = new PageEntity();
		 model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		 com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		 List<TBook> list = this.baseMapper.tableQueryByTime(model);
		 result.setData(list);
		 result.setTotal(page.getTotal());
		 result.setCurrentPage(model.getCurrentPage());
		 result.setPageSize(model.getPageSize());
		 return result;
	 }

	 public static void main(String[] args) {
		 String p = "3:10　约书亚说：“看哪！普天下主的约柜必在你们前头过去，到约旦河里，因此你们就知道在你们中间有永生　神。并且他必在你们面前赶出迦南人、赫人、希未人、比利洗人、革迦撒人、亚摩利人、耶布斯人。   ";
		 System.out.println(p.substring(0,p.indexOf(":")));
		 System.out.println(p.substring(p.indexOf(":") + 1, p.indexOf(" ")));
	 }

 }