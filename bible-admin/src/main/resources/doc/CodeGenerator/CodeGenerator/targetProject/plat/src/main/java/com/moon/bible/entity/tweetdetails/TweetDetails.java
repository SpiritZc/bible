 /** 
 * 模块：圣经-TweetDetails
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tweetdetails;

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
* @Description: tweet_details - 推文详情表
* @author 
* @date 2020-09-04 12:48:19
* @version V1.0  
 */
@Data
@TableName("tweet_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDetails extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** topic_id - 专题id */
    @ApiModelProperty(value="专题id")
    @TableField("topic_id")
    private Long topicId;

    /** title - 标题 */
    @ApiModelProperty(value="标题")
    @TableField("title")
    private String title;

    /** from_author - 文章来源作者 */
    @ApiModelProperty(value="文章来源作者")
    @TableField("from_author")
    private String fromAuthor;

    /** content - 推文内容 */
    @ApiModelProperty(value="推文内容")
    @TableField("content")
    private String content;

    /** img - 推文图片路径 */
    @ApiModelProperty(value="推文图片路径")
    @TableField("img")
    private String img;

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

    /** count - 浏览量 */
    @ApiModelProperty(value="浏览量")
    @TableField("count")
    private Integer count;
}