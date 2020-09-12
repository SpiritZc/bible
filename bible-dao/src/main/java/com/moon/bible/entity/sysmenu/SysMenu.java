 /** 
 * 模块：医院入驻平台-SysMenu
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysmenu;

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
* @Description: sys_menu - 
* @author 
* @date 2020-07-22 08:57:16
* @version V1.0  
 */
@Data
@TableName("sys_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** menu_name - 菜单名称 */
    @ApiModelProperty(value="菜单名称")
    @TableField("menu_name")
    private String menuName;

    /** menu_url - 菜单路径 */
    @ApiModelProperty(value="菜单路径")
    @TableField("menu_url")
    private String menuUrl;

    /** parent_menu_id - 上级菜单id 一级菜单用0表示 */
    @ApiModelProperty(value="上级菜单id 一级菜单用0表示")
    @TableField("parent_menu_id")
    private Long parentMenuId;

    /** menu_icon - 菜单图标 */
    @ApiModelProperty(value="菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

    /** is_hidden - 是否隐藏菜单 1是 2否 默认 2 */
    @ApiModelProperty(value="是否隐藏菜单 1是 2否 默认 2")
    @TableField("is_hidden")
    private Integer isHidden;

    /** rule - 访问规则，1登录后访问 2登录并授权访问 默认 2 */
    @ApiModelProperty(value="访问规则，1登录后访问 2登录并授权访问 默认 2")
    @TableField("rule")
    private Integer rule;

    /** menu_type - 菜单类型 1后台菜单 2其他，默认1，扩展用 */
    @ApiModelProperty(value="菜单类型 1后台菜单 2其他，默认1，扩展用")
    @TableField("menu_type")
    private Integer menuType;

    /** sort - 排序 */
    @ApiModelProperty(value="排序")
    @TableField("sort")
    private Integer sort;

    /** creator - 创建人 */
    @ApiModelProperty(value="创建人")
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

    /** del_flag - 删除标识 1未删除 2已删除 默认 1 */
    @ApiModelProperty(value="删除标识 1未删除 2已删除 默认 1")
    @TableField("del_flag")
    private Integer delFlag;
}