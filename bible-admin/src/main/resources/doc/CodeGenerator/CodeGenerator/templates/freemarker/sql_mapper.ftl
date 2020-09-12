<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!-- 本文件由代码生成器自动完成 -->
<mapper namespace="${basePackage}.mapper.${table.className?lower_case}.${table.className}Mapper" >

<#assign i=0 />
<#assign pk=table.primaryKeys[0] />
<#assign columnList='' />
<#assign valueList='' />
<#list table.columns as column>
	<#if (i>0) >
		<#assign columnList=columnList+ ','/>
		<#assign valueList=valueList+ ','/>
	</#if>
	<#assign columnList=columnList+ '${column.columnName}'/>
	<#if ("${column.jdbcTypeName}"== "BIT") >
	<#assign valueList=valueList+ "#{"+"${column.javaProperty},jdbcType=INTEGER"+"}"/>
	<#else>
	<#assign valueList=valueList+ "#{"+"${column.javaProperty},jdbcType=${column.jdbcTypeName}"+"}"/>
	</#if>
	<#assign i=i+1 />
</#list>
  <!-- 字段与实体的映射 -->
  <resultMap id="BaseResultMap" type="${basePackage}.entity.${table.className?lower_case}.${table.className}">
	<#list table.primaryKeys as key>
     <id column="${key.columnName}" property="${key.javaProperty}" jdbcType="${key.jdbcTypeName}" />
	</#list>
	<#list table.baseColumns as column>
	<#if ("${column.javaProperty}"?ends_with("D8")) >
    <result column="${column.columnName}" property="${column.javaProperty?replace('D8', 'Date' )}" jdbcType="${column.jdbcTypeName}" />
	 <#elseif ("${column.javaProperty}"?ends_with("Dt"))>
	 <result column="${column.columnName}" property="${column.javaProperty?replace('Dt', 'DateTime' )}" jdbcType="${column.jdbcTypeName}" />
	 <#else>
	 <result column="${column.columnName}" property="${column.javaProperty}" jdbcType="${column.jdbcTypeName}" />
	 </#if>
	</#list>
  </resultMap>

  <sql id="sql_where_like">
	<where>
	<#list table.primaryKeys as key>
		<if test="${key.javaProperty} !=null and ${key.javaProperty} !=''">
			and `${key.columnName}` = <#noparse>#{</#noparse>${key.javaProperty},jdbcType=${key.jdbcTypeName}<#noparse>}</#noparse>
		</if>
	</#list>
	<#list table.baseColumns as column>
	<#if ("${column.javaType}"== "String") >
		<if test="${column.javaProperty} !=null and ${column.javaProperty} !=''">
			<#if "${column.javaProperty}"?contains("createTime") || "${column.javaProperty}"?contains("updateTime")>
			<#elseif "${column.javaProperty}"?contains("delF") || "${column.javaProperty}"?contains("Id") || "${column.javaProperty}"?contains("status") || "${column.javaProperty}"?contains("Type")|| "${column.javaProperty}"?contains("creator") || "${column.javaProperty}"?contains("updater")>
			and `${column.columnName}` = <#noparse>#{</#noparse>${column.javaProperty},jdbcType=${column.jdbcTypeName}<#noparse>}</#noparse>
			<#else>
			and `${column.columnName}` like concat(concat("%",<#noparse>#{</#noparse>${column.javaProperty},jdbcType=${column.jdbcTypeName}<#noparse>}</#noparse>),"%")
			</#if>
		</if>
	<#else>
		<#if ("${column.jdbcTypeName}"== "BIT") >
		<if test="${column.javaProperty} !=null">
			and `${column.columnName}` = <#noparse>#{</#noparse>${column.javaProperty},jdbcType=INTEGER<#noparse>}</#noparse>
		</if>
		<#else>
		<if test="${column.javaProperty} !=null">
			<#if "${column.javaProperty}"?contains("createTime") || "${column.javaProperty}"?contains("updateTime")>
			<#else>
			and `${column.columnName}` = <#noparse>#{</#noparse>${column.javaProperty},jdbcType=${column.jdbcTypeName}<#noparse>}</#noparse>
			</#if>
		</if>
		</#if>
	</#if>
	</#list>
	</where>
  </sql>
  <!-- 字段集合 -->
  <sql id="Base_Column_List">
    ${columnList}
  </sql>

  <!-- 根据条件模糊查询 -->
  <select id="searchDataLike" resultMap="BaseResultMap" parameterType="${basePackage}.entity.${table.className?lower_case}.${table.className}">
    select 
    <include refid="Base_Column_List" />
    from `${table.tableName}` 
    <include refid="sql_where_like" />
    <if test="orderSql !=null and orderSql !='' ">   
      order by <#noparse>${</#noparse>orderSql<#noparse>}</#noparse>
    </if>
  </select>

</mapper>
