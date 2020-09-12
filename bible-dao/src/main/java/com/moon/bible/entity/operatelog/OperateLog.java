 /** 
 * 模块：医院入驻平台-OperateLog
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.operatelog;

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
* @Description: operate_log - 
* @author 
* @date 2020-07-27 11:32:06
* @version V1.0  
 */
@Data
@TableName("operate_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** fun_module - 模块 */
    @ApiModelProperty(value="模块")
    @TableField("fun_module")
    private String funModule;

    /** operate_type - 操作类型 */
    @ApiModelProperty(value="操作类型")
    @TableField("operate_type")
    private String operateType;

    /** operate_remark - 操作注释 */
    @ApiModelProperty(value="操作注释")
    @TableField("operate_remark")
    private String operateRemark;

    /** operate_method - 请求操作的方法 */
    @ApiModelProperty(value="请求操作的方法")
    @TableField("operate_method")
    private String operateMethod;

    /** operate_params - 请求参数 */
    @ApiModelProperty(value="请求参数")
    @TableField("operate_params")
    private String operateParams;

    /** operate_time - 操作时间 */
    @ApiModelProperty(value="操作时间")
    @TableField("operate_time")
    private Date operateTime;

    /** operate_status - 操作状态,1:成功 2:失败 默认 1 */
    @ApiModelProperty(value="操作状态,1:成功 2:失败 默认 1")
    @TableField("operate_status")
    private Integer operateStatus;

    /** error_info - 错误消息 */
    @ApiModelProperty(value="错误消息")
    @TableField("error_info")
    private String errorInfo;

    /** result - 返回结果 */
    @ApiModelProperty(value="返回结果")
    @TableField("result")
    private String result;

    /** operate_ip - 请求机器ip */
    @ApiModelProperty(value="请求机器ip")
    @TableField("operate_ip")
    private String operateIp;

    /** execute_time - 执行时长 */
    @ApiModelProperty(value="执行时长")
    @TableField("execute_time")
    private String executeTime;

    /** request_source - 请求来源 1 后台运营 2其他 默认1 */
    @ApiModelProperty(value="请求来源 1 后台运营 2其他 默认1")
    @TableField("request_source")
    private Integer requestSource;

    /** creator - 创建人 */
    @ApiModelProperty(value="创建人")
    @TableField(value = "creator",fill = FieldFill.INSERT)
    private Long creator;

    /** create_time - 创建时间 */
    @ApiModelProperty(value="创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /** del_flag - 删除标识 1未删除 2已删除 默认 1 */
    @ApiModelProperty(value="删除标识 1未删除 2已删除 默认 1")
    @TableField("del_flag")
    private Integer delFlag;
}