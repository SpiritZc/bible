 /** 
 * 模块：bible-SoundDetail
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sounddetail;

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
* @Description: sound_detail - 音频详情表
* @author 
* @date 2020-09-06 03:43:00
* @version V1.0  
 */
@Data
@TableName("sound_detail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoundDetail extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** category_second_id - 有声二级类别id */
    @ApiModelProperty(value="有声二级类别id")
    @TableField("category_second_id")
    private Long categorySecondId;

    /** sound_name - 音频文件名 */
    @ApiModelProperty(value="音频文件名")
    @TableField("sound_name")
    private String soundName;

    /** sound_type - 音频类别1 mp3 */
    @ApiModelProperty(value="音频类别1 mp3")
    @TableField("sound_type")
    private Integer soundType;

    /** sound_url - 音频路径 */
    @ApiModelProperty(value="音频路径")
    @TableField("sound_url")
    private String soundUrl;

    /** sound_author - 音频作者 */
    @ApiModelProperty(value="音频作者")
    @TableField("sound_author")
    private String soundAuthor;

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