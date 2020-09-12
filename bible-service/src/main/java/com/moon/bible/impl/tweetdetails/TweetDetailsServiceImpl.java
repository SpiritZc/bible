package com.moon.bible.impl.tweetdetails;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.api.common.IUploadService;
import com.moon.bible.api.tweethistory.ITweetHistoryService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.entity.tbook.TBook;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.entity.tweethistory.TweetHistory;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.tweetdetails.TweetDetailsMapper;
import com.moon.bible.api.tweetdetails.ITweetDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

/**
 * @author
 * @version V1.0
 * @Description: TweetDetails服务实现
 * @date 2020-08-23 06:49:01
 */
@Service
@Slf4j
public class TweetDetailsServiceImpl extends ServiceImpl<TweetDetailsMapper, TweetDetails> implements ITweetDetailsService {

    @Autowired
    private IUploadService iUploadService;

    @Autowired
    private ITweetHistoryService iTweetHistoryService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param @param model
     * @return BaseEntity
     * @throws
     * @Title: tablePagingQuery
     * @Description: 表格分页查询
     * @author
     */
    @Override
    public PageEntity tablePagingQuery(TweetDetails model) {
        PageEntity result = new PageEntity();
        model.setDelFlag(DelFlagEnum.UNDEL.getCode());
        com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
        List<TweetDetails> list = this.baseMapper.searchDataLike(model);
        result.setData(list);
        result.setTotal(page.getTotal());
        result.setCurrentPage(model.getCurrentPage());
        result.setPageSize(model.getPageSize());
        return result;
    }


    /**
     * <p>Title: getDetail</p>
     * <p>Description: 获取详情</p>
     *
     * @param id
     * @return
     * @author
     */
    @Override
    @Transactional
    public BaseEntity getDetail(Long id,UserInfoDto userInfoDto) throws ParseException {
        BaseEntity result  = new BaseEntity();
        //增加浏览量
        TweetDetails byId = this.getById(id);
        byId.setCount(byId.getCount() + 1);
        this.update(byId);
        //增加历史记录,根据是否在同一天来决定是否新增或者修改时间
        //获取当天日期
        String s = sdf.format(new Date());
        Map<String,Object> map = new HashMap();
        map.put("tweetdetailsId",id);
        map.put("date",s);
        map.put("userId",userInfoDto.getUserId());
        TweetHistory one = iTweetHistoryService.getHistoryByDate(map);
        if (null == one){
            TweetHistory tweetHistory = new TweetHistory();
            tweetHistory.setUserId(userInfoDto.getUserId());
            tweetHistory.setTweetdetailsId(id);
            tweetHistory.setHistoryTime(new Date());
            iTweetHistoryService.save(tweetHistory);
        }
        result.setStatusMsg(MessageUtil.getValue("info.update"));
        return byId;
    }

    /**
     * <p>Title: insert</p>
     * <p>Description: 新增数据</p>
     *
     * @param model
     * @return
     * @author
     */
    @Transactional
    @Override
    public BaseEntity insert(TweetDetails model) {
        BaseEntity result = new BaseEntity();
        if (StringUtils.isBlank(model.getImg())) {
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty", new String[]{"图片"}));
        }
        model.setCount(0);
        this.save(model);
        result.setStatusMsg(MessageUtil.getValue("info.insert"));
        return result;
    }

    /**
     * <p>Title: update</p>
     * <p>Description: 更新数据</p>
     *
     * @param model
     * @return
     * @author
     */
    @Transactional
    @Override
    public BaseEntity update(TweetDetails model) {
        BaseEntity result = new BaseEntity();
        TweetDetails tweetDetails = this.baseMapper.selectById(model.getId());
        if (!StringUtils.isBlank(model.getImg())) {
            //判断是否修改了图片
            if (!tweetDetails.getImg().equals(model.getImg())) {
                try {
                    //删除原图片
                    iUploadService.deleteFile(tweetDetails.getImg());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info(MessageUtil.getValue("error.file.delete", new String[]{"图片"}));
                }
            }
        }
        this.updateById(model);
        result.setStatusMsg(MessageUtil.getValue("info.update"));
        return result;
    }

    /**
     * <p>Title: delete</p>
     * <p>Description: 单条删除数据</p>
     *
     * @param model
     * @return
     * @author
     */
    @Transactional
    @Override
    public BaseEntity delete(Long id) {
        TweetDetails tweetDetails = new TweetDetails();
        tweetDetails.setId(id);
        tweetDetails.setDelFlag(DelFlagEnum.DEL.getCode());
        this.updateById(tweetDetails);
        TweetDetails tweetDetails1 = this.baseMapper.selectById(id);
        //删除图片
        try {
            iUploadService.deleteFile(tweetDetails1.getImg());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(MessageUtil.getValue("error.file.delete", new String[]{"图片"}));
        }
        BaseEntity result = new BaseEntity();
        result.setStatusMsg(MessageUtil.getValue("info.delete"));
        return result;
    }

    /**
     * <p>Title: deleteBatch</p>
     * <p>Description: 批量删除数据</p>
     *
     * @param list
     * @return
     * @author
     */
    @Transactional
    @Override
    public BaseEntity deleteBatch(List<Long> ids) {
        List<TweetDetails> list = new ArrayList<TweetDetails>();
        for (int i = 0; i < ids.size(); i++) {
            TweetDetails tweetDetails = new TweetDetails();
            tweetDetails.setId(ids.get(i));
            tweetDetails.setDelFlag(DelFlagEnum.DEL.getCode());
            list.add(tweetDetails);
        }
        BaseEntity result = new BaseEntity();
        if (list != null && list.size() > 0) {
            this.updateBatchById(list);
        }
        list.forEach(p -> {
            //删除图片
            try {
                iUploadService.deleteFile(p.getImg());
            } catch (Exception e) {
                e.printStackTrace();
                log.info(MessageUtil.getValue("error.file.delete", new String[]{"图片"}));
            }
        });
        result.setStatusMsg(MessageUtil.getValue("info.delete"));
        return result;
    }

    /**
     * @Method increasePageviews
     * @Author zhangcheng
     * @Version 1.0
     * @Description 增加浏览量
     * @Return
     * @Exception
     * @Date 2020-9-4 12:51
     */
    @Override
    @Transactional
    public BaseEntity increasePageviews(Long id) {
        BaseEntity result = new BaseEntity();
        TweetDetails byId = this.getById(id);
        byId.setCount(byId.getCount() + 1);
        this.update(byId);
        result.setStatusMsg(MessageUtil.getValue("info.update"));
        return result;
    }
}