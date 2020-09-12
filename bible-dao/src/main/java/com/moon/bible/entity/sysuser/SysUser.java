 /** 
 * 模块：bible-SysUser
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.sysuser;

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
* @Description: sys_user - 
* @author 
* @date 2020-07-14 07:28:39
* @version V1.0  
 */
@Data
@TableName("sys_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** username - 用户名 */
    @ApiModelProperty(value="用户名")
    @TableField("username")
    private String userName;

    /** password - 密码 */
    @ApiModelProperty(value="密码")
    @TableField("password")
    private String password;

    /** account_name - 账号名(唯一) */
    @ApiModelProperty(value="账号名(唯一)")
    @TableField("account_name")
    private String accountName;

    /** user_phone - 手机号 */
    @ApiModelProperty(value="手机号")
    @TableField("user_phone")
    private String userPhone;

    /** user_email - 邮箱 */
    @ApiModelProperty(value="邮箱")
    @TableField("user_email")
    private String userEmail;

    /** status - 账号状态 1正常 2锁定 默认1 */
    @ApiModelProperty(value="账号状态 1正常 2锁定 默认1")
    @TableField("status")
    private Integer status;

    /** is_admin - 是否是管理员 1是 2否 默认2 */
    @ApiModelProperty(value="是否是管理员 1是 2否 默认2")
    @TableField("is_admin")
    private Integer isAdmin;

    /** creator - 创建人id */
    @ApiModelProperty(value="创建人id")
    @TableField("creator")
    private Long creator;

    /** last_login_time - 上次登录时间 */
    @ApiModelProperty(value="上次登录时间")
    @TableField("last_login_time")
    private Date lastLoginTime;

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

     /** open_id - 微信openId*/
     @ApiModelProperty(value="微信openId")
     @TableField("open_id")
     private String openId;
}