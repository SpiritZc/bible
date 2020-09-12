package com.moon.bible.impl.sysfunction;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.constants.Constants;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.dto.sysmenu.MenuTreeDto;
import com.moon.bible.dto.sysmenu.SysMenuDto;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.enums.FunctionRuleEnum;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.sysfunction.SysFunctionMapper;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import com.moon.bible.enums.DelFlagEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @Description: SysFunction服务实现
* @author 
* @date 2020-07-22 08:57:09
* @version V1.0  
 */
@Service
public class SysFunctionServiceImpl extends ServiceImpl<SysFunctionMapper, SysFunction> implements ISysFunctionService {


	@Autowired
	@Qualifier("shiroFilter")
	@Lazy
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(SysFunction model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<SysFunction> list = this.baseMapper.searchDataLike(model);
		result.setData(list);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}


	/**
	*<p>Title: getDetail</p>
	*<p>Description: 获取详情</p>
	* @author 
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
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity insert(SysFunction model) {
		BaseEntity result = new BaseEntity();
		//校验标识是否已经存在
		if (this.checkRepeat(new QueryWrapper<SysFunction>().eq("function_code", model.getFunctionCode()).eq("function_type",model.getFunctionType())
						.eq("del_flag",DelFlagEnum.UNDEL.getCode())
				,q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.check.exist", new String[] {"功能标识"}));
		}

		this.save(model);
		result.setStatusMsg(MessageUtil.getValue("info.insert"));
		return result;
	}

	/**
	*<p>Title: update</p>
	*<p>Description: 更新数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity update(SysFunction model) {
		BaseEntity result = new BaseEntity();
		//校验标识是否已经存在
		if (this.checkRepeat(new QueryWrapper<SysFunction>().eq("function_code", model.getFunctionCode()).eq("function_type",model.getFunctionType())
						.eq("del_flag",DelFlagEnum.UNDEL.getCode()).ne("id",model.getId())
				,q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.check.exist", new String[] {"功能标识"}));
		}
		this.updateById(model);
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author 
	* @param model
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		SysFunction sysFunction = new SysFunction();
		sysFunction.setId(id);
		sysFunction.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(sysFunction);
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author 
	* @param list
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<SysFunction> list = new ArrayList<SysFunction>();
		for (int i = 0; i < ids.size(); i++) {
			SysFunction sysFunction = new SysFunction();
			sysFunction.setId(ids.get(i));
			sysFunction.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(sysFunction);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	 /**
	  * @Title: getAllFunctions
	  * @Description: 获取所有的功能
	  * @return
	  * @author caiyang
	  * @date 2020-06-08 08:55:47
	  */
	 @Override
	 public List<SysFunction> getAllFunctions() {
		 QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<SysFunction>();
		 queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		 return this.list(queryWrapper);
	 }

	@Override
	public List<SysFunction> getAllFunctionsByType(Integer type) {
		QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<SysFunction>();
		queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		queryWrapper.eq("function_type",type);
		return this.list(queryWrapper);
	}

	/**
	  * @Title: getFunctionsByRole
	  * @Description: 根据角色获取功能
	  * @param roleId
	  * @return
	  * @author caiyang
	  * @date 2020-06-05 05:52:02
	  */
	 @Override
	 public List<SysFunction> getFunctionsByRole(Long roleId) {
		 return this.baseMapper.getFunctionsByRole(roleId);
	 }


	/**
	 * @Title: updatePermission
	 * @Description: 动态更新权限，不需要重启服务
	 * @return
	 * @author caiyang
	 * @date 2020-06-12 03:26:48
	 */
	@Override
	public BaseEntity updatePermission() {
		BaseEntity result = new BaseEntity();
		synchronized (shiroFilterFactoryBean) {
			AbstractShiroFilter shiroFilter;
			try {
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			} catch (Exception e) {
				throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
			}
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
			// 清空老的权限控制
			manager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			Map<String, String> section = new LinkedHashMap<String, String>();
			//获取所有的功能url
			List<SysFunction> list = this.getAllFunctions();
			if (list != null && list.size() > 0) {
				for (SysFunction sysFunction : list) {
					if (FunctionRuleEnum.PUB.getCode()==sysFunction.getRule()) {
						//公开访问，不需要鉴权
						section.put(sysFunction.getFunctionUrl(), Constants.PUBLIC_STRING);
					}else if (FunctionRuleEnum.JWT.getCode() == sysFunction.getRule()) {
						//只需校验登录token
						//平台功能接口
						section.put(sysFunction.getFunctionUrl(), Constants.JWT_STRING);

					}else {
						//需校验登录token和访问权限
						//平台功能接口
						section.put(sysFunction.getFunctionUrl(),
							MessageFormat.format(Constants.PC_ADMIN_PREMISSION_STRING, sysFunction.getFunctionCode()));
					}
				}
			}
			shiroFilterFactoryBean.setFilterChainDefinitionMap(section);
			// 重新构建生成
			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim().replace(" ", "");
				manager.createChain(url, chainDefinition);
			}
		}
		return result;
	}

	@Override
	public List<MenuTreeDto> getMenuFunctions(SysMenuDto sysMenuDto) {
		return this.baseMapper.getMenuFunctions(sysMenuDto);
	}


}