 /** 
 * 模块：bible-TweetHistory
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tweethistory;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import com.moon.bible.base.PageEntity;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**  
* @Description: tweet_history - 推文历史表
* @author 
* @date 2020-09-05 02:29:43
* @version V1.0  
 */
@Data
@TableName("tweet_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetHistory extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** user_id - 用户id */
    @ApiModelProperty(value="用户id")
    @TableField("user_id")
    private Long userId;

    /** tweetdetails_id - 推文id */
    @ApiModelProperty(value="推文id")
    @TableField("tweetdetails_id")
    private Long tweetdetailsId;

    /** history_time - 历史查看时间 */
    @ApiModelProperty(value="历史查看时间")
    @TableField("history_time")
    private Date historyTime;

    /** creator - 创建人id */
    @ApiModelProperty(value="创建人id")
    @TableField("creator")
    private Long creator;

    /** creator_name - 创建人姓名 */
    @ApiModelProperty(value="创建人姓名")
    @TableField(value = "creator_name",fill = FieldFill.INSERT_UPDATE)
    private String creatorName;

    /** create_time - 创建时间 */
    @ApiModelProperty(value="创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /** del_flag - 删除标志 1未删除 2已删除 默认1 */
    @ApiModelProperty(value="删除标志 1未删除 2已删除 默认1")
    @TableField("del_flag")
    private Integer delFlag;
}