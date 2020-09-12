 /** 
 * 模块：${moduleName}-${table.className}
 * 本文件由代码生成器自动完成,不允许进行修改
 */
package ${basePackage}.entity.${table.className?lower_case};

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import ${basePackage}.base.PageEntity;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**  
* @Description: ${table.tableName} - ${table.remarks}
* @author ${author}
* @date ${.now}
* @version V1.0  
 */
@Data
@TableName("${table.tableName}")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${table.className} extends PageEntity {
<#list table.primaryKeys as key>

    /** ${key.columnName} - ${key.remarks} */
    @ApiModelProperty(value="${column.remarks}")
    @TableId(value = "${key.columnName}",type = IdType.ASSIGN_ID)
    private ${key.javaType} ${key.javaProperty};
</#list>
<#list table.baseColumns as column>

    /** ${column.columnName} - ${column.remarks} */
    <#if ("${column.javaProperty}"?contains("createTime")) >
    @ApiModelProperty(value="${column.remarks}")
    @TableField(value = "${column.columnName}",fill = FieldFill.INSERT)
    private ${column.javaType} ${column.javaProperty};
    <#elseif ("${column.javaProperty}"?contains("creatorName"))>
    @ApiModelProperty(value="${column.remarks}")
    @TableField(value = "${column.columnName}",fill = FieldFill.INSERT_UPDATE)
    private ${column.javaType} ${column.javaProperty};
    <#elseif ("${column.javaProperty}"?contains("updateTime"))>
    @ApiModelProperty(value="${column.remarks}")
    @TableField(value = "${column.columnName}",fill = FieldFill.INSERT_UPDATE)
    private ${column.javaType} ${column.javaProperty};
    <#else>
    @ApiModelProperty(value="${column.remarks}")
    @TableField("${column.columnName}")
    private ${column.javaType} ${column.javaProperty};
    </#if>
</#list>
}