 /** 
 * 模块：bible-SysRole
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysrole;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moon.bible.base.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**  
* @Description: sys_role - 
* @author 
* @date 2020-07-14 07:28:29
* @version V1.0  
 */
@Data
@TableName("sys_role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** role_code - 角色代码 */
    @ApiModelProperty(value="角色代码")
    @TableField("role_code")
    private String roleCode;

    /** role_name - 角色名 */
    @ApiModelProperty(value="角色名")
    @TableField("role_name")
    private String roleName;

    /** role_desc - 角色描述 */
    @ApiModelProperty(value="角色描述")
    @TableField("role_desc")
    private String roleDesc;

    /** status - 状态 1正常 2锁定 默认1 */
    @ApiModelProperty(value="状态 1正常 2锁定 默认1")
    @TableField("status")
    private Boolean status;

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