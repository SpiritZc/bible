package com.moon.bible.impl.sysrole;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.moon.bible.api.sysrolefunction.ISysRoleFunctionService;
import com.moon.bible.api.sysrolemenu.ISysRoleMenuService;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.dto.sysrole.UserRoleAuthDto;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.entity.sysrolefunction.SysRoleFunction;
import com.moon.bible.entity.sysrolemenu.SysRoleMenu;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.sysrole.SysRoleMapper;
import com.moon.bible.api.sysrole.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import com.moon.bible.enums.DelFlagEnum;
import com.moon.bible.util.MessageUtil;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

 /**  
* @Description: SysRole服务实现
* @author 
* @date 2020-07-14 07:28:29
* @version V1.0  
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

	@Autowired
	private ISysRoleMenuService iSysRoleMenuService;
	@Autowired
	private ISysFunctionService iSysFunctionService;
	@Autowired
	private ISysRoleFunctionService iSysRoleFunctionService;
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(SysRole model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<SysRole> list = this.baseMapper.searchDataLike(model);
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
	public BaseEntity insert(SysRole model) {
		BaseEntity result = new BaseEntity();
		//判断roleCode是否存在
		if (this.checkRepeat(new QueryWrapper<SysRole>().eq("role_code", model.getRoleCode())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode())
				,q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.check.exist", new String[] {"角色代码"}));
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
	public BaseEntity update(SysRole model) {
		BaseEntity result = new BaseEntity();
		//判断roleCode是否存在
		if (this.checkRepeat(new QueryWrapper<SysRole>().eq("role_code", model.getRoleCode())
						.eq("del_flag",DelFlagEnum.UNDEL.getCode()).ne("id",model.getId())
				,q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.check.exist", new String[] {"角色代码"}));
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
		SysRole sysRole = new SysRole();
		sysRole.setId(id);
		sysRole.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(sysRole);
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
		List<SysRole> list = new ArrayList<SysRole>();
		for (int i = 0; i < ids.size(); i++) {
			SysRole sysRole = new SysRole();
			sysRole.setId(ids.get(i));
			sysRole.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(sysRole);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

     @Override
     public SysRole getUserRole(Long id) {
		return this.baseMapper.getUserRole(id);
     }


	 @Override
	 public List<SysRole> getRoles() {
		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
		queryWrapper.eq("del_flag",DelFlagEnum.UNDEL.getCode());
		 return this.baseMapper.selectList(queryWrapper);
	 }

	 @Override
	 public BaseEntity authed(UserRoleAuthDto userRoleDto) {
		 BaseEntity result = new BaseEntity();
		 //获取当前用户的菜单权限和功能权限
		 QueryWrapper<SysRoleMenu> menuQueryWrapper = new QueryWrapper<SysRoleMenu>();
		 menuQueryWrapper.eq("role_id", userRoleDto.getId());
		 menuQueryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		 List<SysRoleMenu> menus = iSysRoleMenuService.list(menuQueryWrapper);
		 QueryWrapper<SysRoleFunction> functionWrapper = new QueryWrapper<SysRoleFunction>();
		 functionWrapper.eq("role_id", userRoleDto.getId());
		 functionWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		 List<SysRoleFunction> functions = iSysRoleFunctionService.list(functionWrapper);
		 List<String> userAuth = new ArrayList<String>();//用户当前拥有的权限
		 if (menus != null && menus.size() > 0) {
			 for (int i = 0; i < menus.size(); i++) {
				 userAuth.add(String.valueOf(menus.get(i).getMenuId()));
			 }
		 }
		 if (functions != null && functions.size() > 0) {
			 for (int i = 0; i < functions.size(); i++) {
				 userAuth.add("api-"+ functions.get(i).getFunctionId());
			 }
		 }
		 //用户拥有的权限和新授权的权限对比，得到需要添加和删除的权限
		 List<String> addData = this.getDiffData(userRoleDto.getAuthed(), userAuth);//需要添加的权限
		 List<String> delData = this.getDiffData(userAuth, userRoleDto.getAuthed());//需要删除的权限
		 if (delData != null && delData.size() > 0) {
			 for (int i = 0; i < delData.size(); i++) {
				 if (delData.get(i).contains("api-")) {//功能
					 UpdateWrapper<SysRoleFunction> updateWrapper = new UpdateWrapper<SysRoleFunction>();
					 updateWrapper.eq("role_id", userRoleDto.getId());
					 updateWrapper.eq("function_id", delData.get(i).split("-")[1]);
					 SysRoleFunction userRoleFunction = new SysRoleFunction();
					 userRoleFunction.setDelFlag(DelFlagEnum.DEL.getCode());
					 this.iSysRoleFunctionService.update(userRoleFunction, updateWrapper);
				 }else {//菜单
					 UpdateWrapper<SysRoleMenu> updateWrapper = new UpdateWrapper<SysRoleMenu>();
					 updateWrapper.eq("role_id", userRoleDto.getId());
					 updateWrapper.eq("menu_id", delData.get(i));
					 SysRoleMenu userRoleMenu = new SysRoleMenu();
					 userRoleMenu.setDelFlag(DelFlagEnum.DEL.getCode());
					 this.iSysRoleMenuService.update(userRoleMenu,updateWrapper);
				 }
			 }
		 }
		 List<SysRoleFunction> addFunctions = new ArrayList<SysRoleFunction>();
		 List<SysRoleMenu> addMenus = new ArrayList<SysRoleMenu>();
		 if (addData != null && addData.size()>0) {
			 for (int i = 0; i < addData.size(); i++) {
				 if (addData.get(i).contains("api-")) {//功能
					 SysRoleFunction userRoleFunction = new SysRoleFunction();
					 userRoleFunction.setRoleId(userRoleDto.getId());
					 userRoleFunction.setFunctionId(Long.valueOf(addData.get(i).split("-")[1]));
					 addFunctions.add(userRoleFunction);
				 }else {//菜单
					 SysRoleMenu userRoleMenu = new SysRoleMenu();
					 userRoleMenu.setRoleId(userRoleDto.getId());
					 userRoleMenu.setMenuId(Long.valueOf(addData.get(i)));
					 addMenus.add(userRoleMenu);
				 }
			 }
		 }
		 if (addFunctions != null && addFunctions.size() > 0) {
			 this.iSysRoleFunctionService.saveBatch(addFunctions);
		 }
		 if (addMenus != null && addMenus.size() > 0) {
			 this.iSysRoleMenuService.saveBatch(addMenus);
		 }
		 result.setStatusMsg(MessageUtil.getValue("info.role.auth"));
		 return result;
	 }


	 /**
	  * @Title: getDiffData
	  * @Description: 获取集合差值
	  * @param list1
	  * @param list2
	  * @return
	  * @author caiyang
	  * @date 2020-06-11 02:55:49
	  */
	 private List<String> getDiffData(List<String> list1,List<String> list2)
	 {
		 List<String> newlist1 = new ArrayList<String>();
		 newlist1.addAll(list1);
		 List<String> newlist2 = new ArrayList<String>();
		 newlist2.addAll(list2);
		 newlist1.removeAll(newlist2);
		 return newlist1;
	 }
 }