package ${basePackage}.impl.${table.className?lower_case};

import ${basePackage}.entity.${table.className?lower_case}.${table.className};
import ${basePackage}.mapper.${table.className?lower_case}.${table.className}Mapper;
import ${basePackage}.api.${table.className?lower_case}.I${table.className}Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import ${basePackage}.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import ${basePackage}.base.BaseEntity;
import ${basePackage}.base.PageEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ${basePackage}.enums.DelFlagEnum;

 /**  
* @Description: ${table.className}服务实现
* @author ${author}
* @date ${.now}
* @version V1.0  
 */
@Service
public class ${table.className}ServiceImpl extends ServiceImpl<${table.className}Mapper, ${table.className}> implements I${table.className}Service {
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author ${author}
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(${table.className} model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<${table.className}> list = this.baseMapper.searchDataLike(model);
		result.setData(list);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}


	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author ${author}
	* @param id
	* @return
	*/
	@Override
	public BaseEntity getDetail(Long id) {
		return this.getById(id);
	}

	/**
	*<p>Title: insert</p>
	*<p>Description: 新增数据</p>
	* @author ${author}
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity insert(${table.className} model) {
		BaseEntity result = new BaseEntity();
		this.save(model);
		result.setStatusMsg(MessageUtil.getValue("info.insert"));
		return result;
	}

	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author ${author}
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity update(${table.className} model) {
		BaseEntity result = new BaseEntity();
		this.updateById(model);
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author ${author}
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		${table.className} ${table.className?uncap_first} = new ${table.className}();
		${table.className?uncap_first}.setId(id);
		${table.className?uncap_first}.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(${table.className?uncap_first});
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author ${author}
	* @param list
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<${table.className}> list = new ArrayList<${table.className}>();
		for (int i = 0; i < ids.size(); i++) {
			${table.className} ${table.className?uncap_first} = new ${table.className}();
			${table.className?uncap_first}.setId(ids.get(i));
			${table.className?uncap_first}.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(${table.className?uncap_first});
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}
}