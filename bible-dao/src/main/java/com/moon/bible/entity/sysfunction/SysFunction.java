 /** 
 * 模块：医院入驻平台-SysFunction
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysfunction;

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
* @Description: sys_function - 
* @author 
* @date 2020-07-22 08:57:09
* @version V1.0  
 */
@Data
@TableName("sys_function")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysFunction extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** menu_id - 菜单id */
    @ApiModelProperty(value="菜单id")
    @TableField("menu_id")
    private Long menuId;

    /** function_code - 功能标识 */
    @ApiModelProperty(value="功能标识")
    @TableField("function_code")
    private String functionCode;

    /** function_name - 功能名称 */
    @ApiModelProperty(value="功能名称")
    @TableField("function_name")
    private String functionName;

    /** function_url - 功能路径 */
    @ApiModelProperty(value="功能路径")
    @TableField("function_url")
    private String functionUrl;

    /** function_type - 功能类型 1医院后台菜单 2其他，默认1，扩展用 */
    @ApiModelProperty(value="功能类型 1后台菜单 2微信小程序，默认1，扩展用")
    @TableField("function_type")
    private Integer functionType;

    /** rule - 访问规则 1 公开访问 2 登陆后访问 3登陆后并授权访问 默认 3 */
    @ApiModelProperty(value="访问规则 1 公开访问 2 登陆后访问 3登陆后并授权访问 默认 3")
    @TableField("rule")
    private Integer rule;

    /** sort - 排序 */
    @ApiModelProperty(value="排序")
    @TableField("sort")
    private Integer sort;

    /** creator - 创建人 */
    @ApiModelProperty(value="创建人")
    @TableField(value = "creator",fill = FieldFill.INSERT)
    private Long creator;

    /** creator_name - 创建时间 */
    @ApiModelProperty(value="创建时间")
    @TableField(value = "creator_name",fill = FieldFill.INSERT)
    private String creatorName;

    /** del_flag - 删除标识 1未删除 2已删除 默认 1 */
    @ApiModelProperty(value="删除标识 1未删除 2已删除 默认 1")
    @TableField("del_flag")
    private Integer delFlag;
}