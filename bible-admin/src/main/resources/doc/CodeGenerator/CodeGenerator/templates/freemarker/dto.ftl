/*   
 * Copyright (c) 2016-2020 canaanQd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * canaanQd. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with canaanQd.   
 *   
 */ 
package com.canaan.tiss.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.canaan.core.common.AbstractDto;
import java.util.Date;

import lombok.Data;
 /**  
* @Description: ${table.tableName} - ${table.remarks}
* @author 
* @date ${.now}
* @version V1.0  
 */
 @Data
public class ${table.className}Dto extends AbstractDto {
<#list table.primaryKeys as key>

    /** ${key.columnName} - ${key.remarks} */
    private ${key.javaType} ${key.javaProperty};
</#list>
<#list table.baseColumns as column>

    /** ${column.columnName} - ${column.remarks} */
     <#if (column.isString())>
    @NotEmpty(message="{error_notnull};${column.remarks}")
    <#else>
    @NotNull(message="{error_notnull};${column.remarks}")
    </#if>
    <#if (column.isString())>
    @Length(min=1,max=${column.size},message="{error_length};${column.remarks};1;${column.size}")
    </#if>
    <#if ("${column.javaType}"?contains("Integer"))>
     @Min(value=1,message="{error_min};${column.remarks};1")
     @Max(value=9999,message="{error_min};${column.remarks};9999")
    </#if>
     <#if ("${column.javaType}"?contains("Float"))>
     @DecimalMin(value="0.001",message="{error_min};${column.remarks};0")
     @DecimalMax(value="9999.99",message="{error_min};${column.remarks};9999.99")
    </#if>
    <#if ("${column.javaType}"?contains("BigDecimal"))>
     @DecimalMin(value="0.001",message="{error_min};${column.remarks};0")
     @DecimalMax(value="9999.99",message="{error_min};${column.remarks};9999.99")
    </#if>
    private ${column.javaType} ${column.javaProperty};
</#list>

}