 /** 
 * 模块：医院入驻平台-SysRoleMenu
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysrolemenu;

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
* @Description: sys_role_menu - 
* @author 
* @date 2020-07-22 08:57:33
* @version V1.0  
 */
@Data
@TableName("sys_role_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** role_id - 角色id */
    @ApiModelProperty(value="角色id")
    @TableField("role_id")
    private Long roleId;

    /** menu_id - 菜单id */
    @ApiModelProperty(value="菜单id")
    @TableField("menu_id")
    private Long menuId;

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