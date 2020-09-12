 /** 
 * 模块：bible-SysUserRole
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysuserrole;

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
* @Description: sys_user_role - 
* @author 
* @date 2020-07-14 07:28:43
* @version V1.0  
 */
@Data
@TableName("sys_user_role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** user_id - 用户id */
    @ApiModelProperty(value="用户id")
    @TableField("user_id")
    private Long userId;

    /** role_id - 角色id */
    @ApiModelProperty(value="角色id")
    @TableField("role_id")
    private Long roleId;

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