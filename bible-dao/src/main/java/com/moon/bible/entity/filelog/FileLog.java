 /** 
 * 模块：医院入驻平台-FileLog
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package com.moon.bible.entity.filelog;

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
* @Description: file_log - 文件操作记录
* @author 
* @date 2020-07-31 11:28:39
* @version V1.0  
 */
@Data
@TableName("file_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileLog extends PageEntity {

    /** id - 主键 */
    @ApiModelProperty(value="")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** file_name - 文件名 */
    @ApiModelProperty(value="文件名")
    @TableField("file_name")
    private String fileName;

    /** file_size - 文件大小 */
    @ApiModelProperty(value="文件大小")
    @TableField("file_size")
    private Long fileSize;

    /** type - 1上传2下载3删除4查看 */
    @ApiModelProperty(value="1上传2下载3删除4查看")
    @TableField("type")
    private Integer type;

    /** operate_status - 上传状态 1上传成功 2上传失败3删除成功4删除失败 */
    @ApiModelProperty(value="上传状态 1上传成功 2上传失败3删除成功4删除失败")
    @TableField("operate_status")
    private Integer operateStatus;

    /** error_info - 异常信息 */
    @ApiModelProperty(value="异常信息")
    @TableField("error_info")
    private String errorInfo;

    /** result - 返回结果 */
    @ApiModelProperty(value="返回结果")
    @TableField("result")
    private String result;

    /** file_url - 访问路径 */
    @ApiModelProperty(value="访问路径")
    @TableField("file_url")
    private String fileUrl;

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

    /** del_flag - 删除标识 1未删除 2已删除 默认 1 */
    @ApiModelProperty(value="删除标识 1未删除 2已删除 默认 1")
    @TableField("del_flag")
    private Integer delFlag;
}