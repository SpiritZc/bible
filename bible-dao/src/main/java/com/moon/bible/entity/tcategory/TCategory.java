 /** 
 * 模块：医院入驻平台-TCategory
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.tcategory;

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
* @Description: t_category - 小说类别表
* @author 
* @date 2020-07-30 10:22:12
* @version V1.0  
 */
@Data
@TableName("t_category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TCategory extends PageEntity {

    /** id - 类别id */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** name - 类别名称 */
    @ApiModelProperty(value="类别名称")
    @TableField("name")
    private String name;

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
}