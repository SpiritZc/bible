package com.moon.bible.api.userloginlog;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moon.bible.entity.userloginlog.UserLoginLog;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.GenericService;
import com.moon.bible.base.PageEntity;

 /**  
* @Description: UserLoginLog服务接口
* @author 
* @date 2020-07-18 03:21:00
* @version V1.0  
 */
public interface IUserLoginLogService extends IService<UserLoginLog> ,GenericService<UserLoginLog>{

	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @throws 
	*/ 
	PageEntity tablePagingQuery(UserLoginLog model);

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
	BaseEntity insert(UserLoginLog model);
	
	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author caiyang
	* @param model
	*/
	BaseEntity update(UserLoginLog model);
	
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
