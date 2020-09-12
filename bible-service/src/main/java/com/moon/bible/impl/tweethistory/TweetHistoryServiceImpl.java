package com.moon.bible.impl.tweethistory;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.tweetdetails.TweetDetailsHistoryDto;
import com.moon.bible.entity.tweetdetails.TweetDetails;
import com.moon.bible.entity.tweethistory.TweetHistory;
import com.moon.bible.entity.tweettopic.TweetTopic;
import com.moon.bible.mapper.tweethistory.TweetHistoryMapper;
import com.moon.bible.api.tweethistory.ITweetHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;

/**
 * @author
 * @version V1.0
 * @Description: TweetHistory服务实现
 * @date 2020-09-05 02:29:43
 */
@Service
public class TweetHistoryServiceImpl extends ServiceImpl<TweetHistoryMapper, TweetHistory> implements ITweetHistoryService {

    /**
     * @param @param model
     * @return BaseEntity
     * @throws
     * @Title: tablePagingQuery
     * @Description: 表格分页查询
     * @author
     */
    @Override
    public PageEntity tablePagingQuery(TweetHistory model) {
        PageEntity result = new PageEntity();
        model.setDelFlag(DelFlagEnum.UNDEL.getCode());
        com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
        List<TweetHistory> list = this.baseMapper.searchDataLike(model);
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
    public BaseEntity getDetail(Long id) {
        return this.getById(id);
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
    public BaseEntity insert(TweetHistory model) {
        BaseEntity result = new BaseEntity();
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
    public BaseEntity update(TweetHistory model) {
        BaseEntity result = new BaseEntity();
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
        TweetHistory tweetHistory = new TweetHistory();
        tweetHistory.setId(id);
        tweetHistory.setDelFlag(DelFlagEnum.DEL.getCode());
        this.updateById(tweetHistory);
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
        List<TweetHistory> list = new ArrayList<TweetHistory>();
        for (int i = 0; i < ids.size(); i++) {
            TweetHistory tweetHistory = new TweetHistory();
            tweetHistory.setId(ids.get(i));
            tweetHistory.setDelFlag(DelFlagEnum.DEL.getCode());
            list.add(tweetHistory);
        }
        BaseEntity result = new BaseEntity();
        if (list != null && list.size() > 0) {
            this.updateBatchById(list);
        }
        result.setStatusMsg(MessageUtil.getValue("info.delete"));
        return result;
    }

    /**
     * 功能描述: 根据推文id和日期查询历史记录<br>
     * 〈〉
     *
     * @Param: [map]
     * @Return: com.moon.bible.entity.tweethistory.TweetHistory
     * @Author: Administrator
     * @Date: 2020/9/5 14:59
     */
    @Override
    public TweetHistory getHistoryByDate(Map<String, Object> map) {
        TweetHistory tweetHistory = this.baseMapper.getHistoryByDate(map);
        return tweetHistory;
    }

    /**
     * 功能描述: 查询推文历史记录<br>
     * 〈〉
     *
     * @Param: [userInfoDto]
     * @Return: java.util.List<java.util.Map   <   java.lang.String   ,   java.lang.Object>>
     * @Author: Administrator
     * @Date: 2020/9/5 15:10
     */
    @Override
    public List<Map<String, Object>> getHistoryList(UserInfoDto userInfoDto) {
        List<TweetDetailsHistoryDto> tweetDetails = this.baseMapper.getHistoryList(userInfoDto.getUserId());
        Map<String, List<TweetDetailsHistoryDto>> collect = tweetDetails.stream().collect(Collectors.groupingBy(TweetDetailsHistoryDto::getHistoryDate));
        List<Map<String, Object>> maps = new ArrayList<>();
        collect.forEach((k, v) -> {
            Map<String, Object> mapRes = new HashMap<>();
            mapRes.put("time", k);
            mapRes.put("result", v);
            maps.add(mapRes);
        });
        return maps;
    }

    /**
     * @Method delete
     * @Author zhangcheng
     * @Version 1.0
     * @Description 删除历史记录
     * @Return
     * @Exception
     * @Date 2020-9-10 10:39
     */
    @Override
    @Transactional
    public BaseEntity delete(Long tweetDteailsId, UserInfoDto userInfoDto) {
        TweetHistory tweetHistory = new TweetHistory();
        UpdateWrapper<TweetHistory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("tweetdetails_id", tweetDteailsId);
        updateWrapper.eq("user_id", userInfoDto.getUserId());
        tweetHistory.setDelFlag(DelFlagEnum.DEL.getCode());
        this.update(tweetHistory, updateWrapper);
        BaseEntity result = new BaseEntity();
        result.setStatusMsg(MessageUtil.getValue("info.delete"));
        return result;
    }

    /**
     * @Method historyCount
     * @Author zhangcheng
     * @Version 1.0
     * @Description 查询七天历史记录总数
     * @Return
     * @Exception
     * @Date 2020-9-10 10:39
     */
    @Override
    public Integer historyCount(UserInfoDto userInfoDto) {
        return this.baseMapper.getHistoryCount(userInfoDto.getUserId());
    }
}