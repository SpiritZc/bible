 /** 
 * 模块：bible-TweetTopic
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tweettopic;

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
* @Description: tweet_topic - 推文专题表
* @author 
* @date 2020-08-23 06:48:47
* @version V1.0  
 */
@Data
@TableName("tweet_topic")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetTopic extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** topic_name - 专题名 */
    @ApiModelProperty(value="专题名")
    @TableField("topic_name")
    private String topicName;

     /** creator - 创建人id */
     @ApiModelProperty(value="创建人id")
     @TableField( value = "creator",fill = FieldFill.INSERT)
     private Long creator;

     /** creator_name - 创建人姓名 */
     @ApiModelProperty(value="创建人姓名")
     @TableField(value = "creator_name",fill = FieldFill.INSERT)
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