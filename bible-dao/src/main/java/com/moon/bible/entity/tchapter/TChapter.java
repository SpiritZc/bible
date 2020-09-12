 /** 
 * 模块：bible-TChapter
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tchapter;

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
* @Description: t_chapter - 卷章节内容表
* @author 
* @date 2020-08-22 08:31:36
* @version V1.0  
 */
@Data
@TableName("t_chapter")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TChapter extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** volume - 卷 */
    @ApiModelProperty(value="卷")
    @TableField("volume")
    private String volume;

    /** chapter - 章 */
    @ApiModelProperty(value="章")
    @TableField("chapter")
    private String chapter;

    /** section - 节 */
    @ApiModelProperty(value="节")
    @TableField("section")
    private String section;

    /** book_id - 小说id */
    @ApiModelProperty(value="小说id")
    @TableField("book_id")
    private Long bookId;

    /** word_count - 字数 */
    @ApiModelProperty(value="字数")
    @TableField("word_count")
    private Integer wordCount;

    /** intro - 章节内容 */
    @ApiModelProperty(value="章节内容")
    @TableField("intro")
    private String intro;

    /** creator - 创建人id */
    @ApiModelProperty(value="创建人id")
    @TableField(value = "creator",fill = FieldFill.INSERT)
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

    /** read_num - 阅读数 */
    @ApiModelProperty(value="阅读数")
    @TableField("read_num")
    private Integer readNum;
}