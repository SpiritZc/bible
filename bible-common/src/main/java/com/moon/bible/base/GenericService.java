package com.moon.bible.base;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 所有自定义Service的顶级接口，封装常用的增删查改操作
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 *
 * @author 蔡阳
 * @data 2019年9月30日08:59:41
 */
public interface GenericService <Model extends BaseEntity>{
	
	/**  
	 * @Title: tablePagingQuery
	 * @Description: 分页查询
	 * @param model
	 * @return
	 * @author caiyang
	 * @date 2020年5月14日 
	 */ 
	PageEntity tablePagingQuery(Model model);

	/**
	 * @Method checkRepeat
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 字段检查工具类
	 * @Return java.lang.Boolean
	 * @Exception
	 * @Date 2020-6-10 11:34
	 */
	default Boolean checkRepeat(QueryWrapper<Model> queryWrapper, Function<QueryWrapper<Model>, Boolean> function){
		return function.apply(queryWrapper);
	}

}
