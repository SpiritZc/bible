/** 
 * 模块：${moduleName}-${table.className}
 */
package ${basePackage}.controller.${table.className?lower_case};

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackage}.base.BaseController;
import ${basePackage}.base.BaseEntity;
import ${basePackage}.constants.Constants;
import ${basePackage}.entity.${table.className?lower_case}.${table.className};
import ${basePackage}.annotation.Check;
import ${basePackage}.annotation.MethodLog;
import ${basePackage}.api.${table.className?lower_case}.I${table.className}Service;
import io.swagger.annotations.ApiOperation;
 /**  
* @Description: ${table.className}controller类
* @author ${author}
* @date ${.now}
* @version V1.0  
 */
@RestController
@RequestMapping("/api/${table.className?uncap_first}")
public class ${table.className}Controller extends BaseController {

	/**
	 * i${table.className}Service服务注入
	 */
	@Autowired
	private I${table.className}Service i${table.className}Service;

	/** 
	* @Description: 分页查询表格
	* @param ${table.className}
	* @param @return  
	* @return BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-获取表格数据", notes="获取表格数据")
	@RequestMapping(value = "/getTableList",method = RequestMethod.POST)
	public BaseEntity getTableList(@RequestBody ${table.className} model)
	{
		
		BaseEntity result = new BaseEntity();
		result = i${table.className}Service.tablePagingQuery(model);
		return result;
	}

	/** 
	* @Description: 获取详细信息
	* @param id
	* @param @return  BaseEntity
	* @return  
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-获取详细信息", notes="根据id获取详细信息")
	@RequestMapping(value = "/getDetail",method = RequestMethod.GET)
	<#assign layverify="" /> 
	<#assign layverify = layverify + "${table.primaryKeys[0].javaProperty}:required#主键ID"/>
	@Check({"${layverify}"})
	public BaseEntity getDetail(@RequestParam Long ${table.primaryKeys[0].javaProperty}) throws Exception
	{
		BaseEntity result = i${table.className}Service.getDetail(${table.primaryKeys[0].javaProperty});
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 新增
	* @param ${table.className}
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-新增数据", notes="新增数据")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	@MethodLog(module="${table.className}",remark="新增",operateType=Constants.OPERATE_TYPE_ADD)
	<#assign layverify="" /> 
	<#list table.baseColumns as column>
	     <#if ("${column.columnName}" != "${table.primaryKeys[0].columnName}") >
		<#if (column.isString()) >
			<#if "${column.javaProperty}"?contains("delF") || "${column.javaProperty}"?contains("createTime") || "${column.javaProperty}"?contains("updateTime") || "${column.javaProperty}"?contains("Id") || "${column.javaProperty}"?contains("creator") || "${column.javaProperty}"?contains("updater")>
			<#else>
			<#assign layverify = layverify +'"'+ "${column.javaProperty}:required#${column.remarks};length#${column.remarks}#${column.size}"/>
			<#if ("${column.javaProperty?lower_case}"?contains("mobile") || "${column.javaProperty?lower_case}"?contains("phone")) >
				<#assign layverify = layverify + ";mobile"/>
			</#if>
			<#if (column.isString()&& ("${column.javaProperty?lower_case}"?contains("email"))) >
				<#assign layverify = layverify + ";email"/>
			</#if>
			<#assign layverify = layverify + '",'/>
			</#if>
		</#if>
		<#if "${column.javaType?lower_case}"?contains("bigdecimal") >
			<#assign integer="" /> 
			<#list 1..column.size as t>
			<#assign integer = integer + '9'/>
			</#list>
			<#assign digits="" /> 
			<#list 1..column.decimalDigits as t>
			<#assign digits = digits + '9'/>
			</#list>
			<#assign layverify = layverify +'"'+ "${column.javaProperty}:required#${column.remarks};max#${column.remarks}#${integer}.${digits}}"/>
			<#assign layverify = layverify + '",'/>
		</#if>
	     </#if> 
	</#list>
	@Check({${layverify}})
	public BaseEntity insert(@RequestBody ${table.className} model) throws Exception
	{
		BaseEntity result = i${table.className}Service.insert(model);
		return result;
	}

	/**
	* @throws Exception  
	* @Description: 更新
	* @param ${table.className}
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-更新数据", notes="更新数据")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@MethodLog(module="${table.className}",remark="更新",operateType=Constants.OPERATE_TYPE_UPDATE)
	<#assign layverify="" />
	<#assign layverify = layverify +'"'+ "${table.primaryKeys[0].javaProperty}:required#主键ID"+'",'/>
	<#list table.baseColumns as column>
	      <#if ("${column.columnName}" != "${table.primaryKeys[0].columnName}") >
		<#if (column.isString()) >
		<#if "${column.javaProperty}"?contains("delF") || "${column.javaProperty}"?contains("createTime") || "${column.javaProperty}"?contains("updateTime") || "${column.javaProperty}"?contains("Id") ||  "${column.javaProperty}"?contains("creator") || "${column.javaProperty}"?contains("updater")>	
		<#else>
		<#assign layverify = layverify +'"'+ "${column.javaProperty}:required#${column.remarks};length#${column.remarks}#${column.size}"/>
			<#if ("${column.javaProperty?lower_case}"?contains("mobile") || "${column.javaProperty?lower_case}"?contains("phone")) >
				<#assign layverify = layverify + ";mobile"/>
			</#if>
			<#if (column.isString()&& ("${column.javaProperty?lower_case}"?contains("email"))) >
				<#assign layverify = layverify + ";email"/>
			</#if>
			<#assign layverify = layverify + '",'/>
		</#if>
		</#if>
		<#if "${column.javaType?lower_case}"?contains("bigdecimal") >
			<#assign integer="" /> 
			<#list 1..column.size as t>
			<#assign integer = integer + '9'/>
			</#list>
			<#assign digits="" /> 
			<#list 1..column.decimalDigits as t>
			<#assign digits = digits + '9'/>
			</#list>
			<#assign layverify = layverify +'"'+ "${column.javaProperty}:required#${column.remarks};max#${column.remarks}#${integer}.${digits}}"/>
			<#assign layverify = layverify + '",'/>
		</#if>
	      </#if>
	</#list>
	@Check({${layverify}})
	public BaseEntity update(@RequestBody ${table.className} model) throws Exception
	{
		BaseEntity result = i${table.className}Service.update(model);
		return result;
	}

	/** 
	* @Description: 单条删除
	* @param id
	* @return  BaseEntity
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-单条删除数据", notes="根据主键删除数据")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@MethodLog(module="${table.className}",remark="单条删除",operateType=Constants.OPERATE_TYPE_DELETE)
	<#assign layverify="" /> 
	<#assign layverify = layverify + "${table.primaryKeys[0].javaProperty}:required#主键ID"/>
	@Check({"${layverify}"})
	public BaseEntity delete(@RequestParam Long ${table.primaryKeys[0].javaProperty})
	{
		BaseEntity result = i${table.className}Service.delete(${table.primaryKeys[0].javaProperty});
		return result;
	}

	/** 
	* @Description: 批量删除
	* @param ${table.className} 
	* @return BaseEntity 
	* @throws 
	*/ 
	@ApiOperation(value="${table.className}-批量删除", notes="批量删除数据")
	@RequestMapping(value = "/deletebatch",method = RequestMethod.POST)
	@MethodLog(module="${table.className}",remark="批量删除",operateType=Constants.OPERATE_TYPE_BATCHDELETE)
	public BaseEntity deletebatch(@RequestBody List<Long> ids)
	{
		BaseEntity result = i${table.className}Service.deleteBatch(ids);
		return result;
	}
}
