package ${basePackage}.api.${table.className?lower_case};
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import ${basePackage}.entity.${table.className?lower_case}.${table.className};
import ${basePackage}.base.BaseEntity;
import ${basePackage}.base.GenericService;
import ${basePackage}.base.PageEntity;

 /**  
* @Description: ${table.className}服务接口
* @author ${author}
* @date ${.now}
* @version V1.0  
 */
public interface I${table.className}Service extends IService<${table.className}> ,GenericService<${table.className}>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(${table.className} model);

	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author caiyang
	* @param id
	* @return
	*/
	BaseEntity getDetail(Long id);
	
	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity insert(${table.className} model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(${table.className} model);
	
	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author caiyang
	* @param model
	* @return
	*/
	BaseEntity delete(Long id);
	
	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author caiyang
	* @param list
	* @return
	*/
	BaseEntity deleteBatch(List<Long> ids);
}
