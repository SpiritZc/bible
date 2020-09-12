package com.moon.bible.base;

import java.util.List;

/**
 * 所有自定义Dao的顶级接口
 * <p>封装常用的增删查改操作，DAO类需要继承GenericDao</p>
 * <ul><li>Model : 代表数据库中的表所映射的Java对象类型
 * <li>PK :代表对象的主键类型</ul>
 * @author 蔡阳
 * @data 2020年6月1日11:12:54
 */
public interface GenericMapper <Model extends BaseEntity> {

	/**
     * 通过条件，查询数据集合，返回分页数据，字符串参数模糊查询
     *
     * @param model 包含查询条件的对象实体
     * @return 实体集合
     */
    List<Model> searchDataLike(final Model model);
}
