 /** 
 * 模块：bible-TBook
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tbook;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import com.moon.bible.base.PageEntity;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**  
* @Description: t_book - 小说表
* @author 
* @date 2020-07-26 05:37:41
* @version V1.0  
 */
@Data
@TableName("t_book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TBook extends PageEntity {

    /** id - 书籍id */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** book_name - 书籍名称 */
    @ApiModelProperty(value="书籍名称")
    @TableField("book_name")
    private String bookName;

    /** pinyin - 拼音 */
    @ApiModelProperty(value="拼音")
    @TableField("pinyin")
    private String pinyin;

    /** cid - 书籍类别 */
    @ApiModelProperty(value="书籍类别")
    @TableField("cid")
    private Long cid;

    /** author - 作者 */
    @ApiModelProperty(value="作者")
    @TableField("author")
    private String author;

    /** image - 图片路径 */
    @ApiModelProperty(value="图片路径")
    @TableField("image")
    private String image;

    /** publishing - 出版社 */
    @ApiModelProperty(value="出版社")
    @TableField("publishing")
    private String publishing;

    /** description - 描述 */
    @ApiModelProperty(value="描述")
    @TableField("description")
    private String description;

    /** state - 书籍状态（1 已上架 2 已下架 默认值1 ） */
    @ApiModelProperty(value="书籍状态（1 已上架 2 已下架 默认值1 ）")
    @TableField("state")
    private Integer state;

    /** deployTime - 上架时间 */
    @ApiModelProperty(value="上架时间")
    @TableField("deployTime")
//    @JSONField(format = "yyyy-MM-dd")
    private Date deploytime;

    /** hits - 浏览次数 */
    @ApiModelProperty(value="浏览次数")
    @TableField("hits")
    private Integer hits;

    /** url - 书籍的路径 */
    @ApiModelProperty(value="书籍的路径")
    @TableField("url")
    private String url;

    /** creator - 创建人 */
    @ApiModelProperty(value="创建人")
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

    /** del_flag - 删除标识 1未删除 2已删除 默认 1 */
    @ApiModelProperty(value="删除标识 1未删除 2已删除 默认 1")
    @TableField("del_flag")
    private Integer delFlag;
}