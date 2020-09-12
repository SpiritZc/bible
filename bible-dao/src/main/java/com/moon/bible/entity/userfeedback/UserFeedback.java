 /** 
 * 模块：bible-UserFeedback
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.userfeedback;

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
* @Description: user_feedback - 用户意见反馈表
* @author 
* @date 2020-09-12 12:17:50
* @version V1.0  
 */
@Data
@TableName("user_feedback")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedback extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** user_id - 用户id */
    @ApiModelProperty(value="用户id")
    @TableField("user_id")
    private Long userId;

    /** send_time - 反馈时间 */
    @ApiModelProperty(value="反馈时间")
    @TableField("send_time")
    private Date sendTime;

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