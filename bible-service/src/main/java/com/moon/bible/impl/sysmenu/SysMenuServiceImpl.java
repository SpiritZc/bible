package com.moon.bible.impl.sysmenu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.moon.bible.api.sysrolefunction.ISysRoleFunctionService;
import com.moon.bible.api.sysrolemenu.ISysRoleMenuService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.dto.sysmenu.AuthTreeDto;
import com.moon.bible.dto.sysmenu.IndexMenuTreeDto;
import com.moon.bible.dto.sysmenu.MenuTreeDto;
import com.moon.bible.dto.sysmenu.SysMenuDto;
import com.moon.bible.entity.sysmenu.SysMenu;
import com.moon.bible.entity.sysrole.SysRole;
import com.moon.bible.entity.sysrolefunction.SysRoleFunction;
import com.moon.bible.entity.sysrolemenu.SysRoleMenu;
import com.moon.bible.enums.*;
import com.moon.bible.impl.sysrolefunction.SysRoleFunctionServiceImpl;
import com.moon.bible.mapper.sysfunction.SysFunctionMapper;
import com.moon.bible.mapper.sysmenu.SysMenuMapper;
import com.moon.bible.api.sysmenu.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bible.mapper.sysrolefunction.SysRoleFunctionMapper;
import org.assertj.core.data.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.bible.util.MessageUtil;
import com.github.pagehelper.PageHelper;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
* @Description: SysMenu服务实现
* @author 
* @date 2020-07-22 08:57:16
* @version V1.0  
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	@Autowired
	private ISysRoleFunctionService iSysRoleFunctionService;

	@Autowired
	private ISysFunctionService iSysFunctionService;

	@Autowired
	private ISysRoleMenuService iSysRoleMenuService;

	/**
	 * @param @param model
	 * @return BaseEntity
	 * @throws
	 * @Title: tablePagingQuery
	 * @Description: 表格分页查询
	 * @author
	 */
	@Override
	public PageEntity tablePagingQuery(SysMenu model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<SysMenu> list = this.baseMapper.searchDataLike(model);
		result.setData(list);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}


	/**
	 * <p>Title: getDetail</p>
	 * <p>Description: 获取详情</p>
	 *
	 * @param id
	 * @return
	 * @author
	 */
	@Override
	public BaseEntity getDetail(Long id) {
		return this.getById(id);
	}

	/**
	 * <p>Title: insert</p>
	 * <p>Description: 新增数据</p>
	 *
	 * @param model
	 * @return
	 * @author
	 */
	@Transactional
	@Override
	public BaseEntity insert(SysMenu model) {
		BaseEntity result = new BaseEntity();
		if (model.getParentMenuId() == null) {
			model.setParentMenuId(0L);
		}
		this.save(model);
		result.setStatusMsg(MessageUtil.getValue("info.insert"));
		return result;
	}

	/**
	 * <p>Title: update</p>
	 * <p>Description: 更新数据</p>
	 *
	 * @param model
	 * @return
	 * @author
	 */
	@Transactional
	@Override
	public BaseEntity update(SysMenu model) {
		BaseEntity result = new BaseEntity();
		this.updateById(model);
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	 * <p>Title: delete</p>
	 * <p>Description: 单条删除数据</p>
	 *
	 * @param model
	 * @return
	 * @author
	 */
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setId(id);
		sysMenu.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(sysMenu);
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	 * <p>Title: deleteBatch</p>
	 * <p>Description: 批量删除数据</p>
	 *
	 * @param list
	 * @return
	 * @author
	 */
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<SysMenu> list = new ArrayList<SysMenu>();
		for (int i = 0; i < ids.size(); i++) {
			SysMenu sysMenu = new SysMenu();
			sysMenu.setId(ids.get(i));
			sysMenu.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(sysMenu);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	 * @param userInfoDto
	 * @return
	 * @Title: getIndexMenu
	 * @Description: 获取首页菜单树
	 * @author caiyang
	 * @date 2020-06-11 05:21:25
	 * @see
	 */
	@Override
	public List<IndexMenuTreeDto> getIndexMenu(UserInfoDto userInfoDto) {
		SysMenuDto sysMenuDto = new SysMenuDto();
		if (YesNoEnum.YES.getCode() == userInfoDto.getIsAdmin()) {
			//超级管理员
			sysMenuDto.setIsAdmin(YesNoEnum.YES.getCode());
		} else {
			sysMenuDto.setRoleId(userInfoDto.getRoleId());
		}
		sysMenuDto.setMenuType(MenuTypeEnum.ADMIN.getCode());
		sysMenuDto.setParentMenuId(0L);
		List<IndexMenuTreeDto> list = this.baseMapper.getIndexMenu(sysMenuDto);
		this.getSubIndexMenu(list, userInfoDto);
		return list;
	}

	@Override
	public BaseEntity getAuthTree(SysRole sysRole, UserInfoDto userInfoDto) {
		AuthTreeDto authTreeDto = new AuthTreeDto();
		List<String> authed = new ArrayList<String>();
		//获取角色已经授权的功能
		QueryWrapper<SysRoleFunction> userRoleFunctionWrapper = new QueryWrapper<SysRoleFunction>();
		userRoleFunctionWrapper.eq("role_id", sysRole.getId());
		userRoleFunctionWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		List<SysRoleFunction> functions = iSysRoleFunctionService.list(userRoleFunctionWrapper);
		if (functions != null && functions.size() > 0) {
			for (SysRoleFunction userRoleFunction : functions) {
				authed.add("api-" + userRoleFunction.getFunctionId());
			}
		}
		authTreeDto.setAuthed(authed);
		//当前用户有权限的菜单和功能
		SysMenuDto sysMenuDto = new SysMenuDto();
		if (YesNoEnum.YES.getCode() == userInfoDto.getIsAdmin()) {
			//超级用户有所有的菜单和功能权限
			sysMenuDto.setIsAdmin(YesNoEnum.YES.getCode());
		} else {
			sysMenuDto.setRoleId(userInfoDto.getRoleId());
		}
		sysMenuDto.setParentMenuId(0L);
		sysMenuDto.setMenuType(MenuTypeEnum.ADMIN.getCode());
		sysMenuDto.setRule(MenuRuleEnum.AUTH.getCode());
		//获取有权限的顶级菜单
		List<MenuTreeDto> list = this.baseMapper.getMenuTree(sysMenuDto);
		this.getSubAuthTree(list, authed, userInfoDto, userInfoDto.getRoleId());
		authTreeDto.setTreeData(list);
		authTreeDto.setAuthed(authed);
		return authTreeDto;
	}

	/**
	 * @Title: getAllMenus
	 * @Description: 获取所有的后台菜单
	 * @return
	 * @author caiyang
	 * @date 2020-06-16 01:20:30
	 */
	@Override
	public List<SysMenu> getAllMenus() {
		QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>();
		queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
		queryWrapper.eq("is_hidden", YesNoEnum.NO.getCode());
		queryWrapper.eq("menu_type", MenuTypeEnum.ADMIN.getCode());
		return this.list(queryWrapper);
	}


	/**
	 * @param list
	 * @param authed
	 * @param userInfoDto
	 * @Title: getSubAuthTree
	 * @Description: 递归获取所有的子菜单和功能
	 * @author caiyang
	 * @date 2020-06-11 08:36:50
	 */
	private void getSubAuthTree(List<MenuTreeDto> list, List<String> authed, UserInfoDto userInfoDto, Long roleId) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				//获取菜单下的所有子菜单
				SysMenuDto sysMenuDto = new SysMenuDto();
				sysMenuDto.setParentMenuId(list.get(i).getId());
				sysMenuDto.setRule(MenuRuleEnum.AUTH.getCode());
				sysMenuDto.setMenuType(MenuTypeEnum.ADMIN.getCode());
				if (YesNoEnum.YES.getCode() == userInfoDto.getIsAdmin()) {
					//超级用户有所有的菜单和功能权限
					sysMenuDto.setIsAdmin(YesNoEnum.YES.getCode());
				} else {
					sysMenuDto.setRoleId(roleId);
				}
				List<MenuTreeDto> subMenus = this.baseMapper.getMenuTree(sysMenuDto);
				if (subMenus != null && subMenus.size() > 0) {
					this.getSubAuthTree(subMenus, authed, userInfoDto, roleId);
				}
				//获取菜单下有权限的接口
				sysMenuDto.setId(list.get(i).getId());
				sysMenuDto.setRule(FunctionRuleEnum.JWTAUTH.getCode());
				List<MenuTreeDto> functions = iSysFunctionService.getMenuFunctions(sysMenuDto);
				if (subMenus == null) {
					list.get(i).setChildren(functions);
				} else {
					if (functions == null || functions.size() == 0) {
						//如果该菜单下没有接口则判断该菜单是否有权限，顶级菜单除外
						if (0 != list.get(i).getParentMenuId()) {
							QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<SysRoleMenu>();
							queryWrapper.eq("role_id", roleId);
							queryWrapper.eq("menu_id", list.get(i).getId());
							queryWrapper.eq("del_flag", DelFlagEnum.UNDEL.getCode());
							SysRoleMenu userRoleMenu = iSysRoleMenuService.getOne(queryWrapper);
							if (userRoleMenu != null) {
								authed.add(String.valueOf(list.get(i).getId()));
							}
						}
					}
					subMenus.addAll(functions);
					list.get(i).setChildren(subMenus);
				}
			}
		}
	}




//	 private void getEachAuthLayer(Long roleId,AuthMenuTreeDto authMenuTreeDto,Integer isAdmin) {
//	 	//查询下级菜单
//		 List<AuthMenuTreeDto> menuByRole = this.baseMapper.getAuthMenuByRole(authMenuTreeDto.getId(), roleId,isAdmin);
//		 if (null!=menuByRole && menuByRole.size()>0){
//		 	//插入下级菜单
//			 authMenuTreeDto.setChildren(menuByRole);
//			 menuByRole.stream().forEach(p->{
//				 getEachAuthLayer(roleId,p,isAdmin);
//			 });
//		 }
//		 //查询该菜单下，拥有权限的功能
//		 List<AuthMenuTreeDto> authFunctionsByRole = sysFunctionMapper.getAuthFunctionsByRole(authMenuTreeDto.getId(), roleId,isAdmin);
//		 if (null!=authFunctionsByRole && authFunctionsByRole.size()>0){
//			 authMenuTreeDto.setChildren(authFunctionsByRole);
//		 }
//	 }

	 /**
	  * @Title: getSubIndexMenu
	  * @Description: 获取子菜单树
	  * @param list
	  * @param userInfoDto
	  * @author caiyang
	  * @date 2020-06-11 05:55:27
	  */
	 private void getSubIndexMenu(List<IndexMenuTreeDto> list,UserInfoDto userInfoDto)
	 {
		 if (list != null && list.size() > 0) {
			 for (int i = 0; i < list.size(); i++) {
				 SysMenuDto sysMenuDto = new SysMenuDto();
				 if (YesNoEnum.YES.getCode() == userInfoDto.getIsAdmin()) {
					 //超级管理员
					 sysMenuDto.setIsAdmin(YesNoEnum.YES.getCode());
				 }else {
					 sysMenuDto.setRoleId(userInfoDto.getRoleId());
				 }
				 sysMenuDto.setParentMenuId(list.get(i).getId());
				 List<IndexMenuTreeDto> subMenus =  this.baseMapper.getIndexMenu(sysMenuDto);
				 list.get(i).setSubs(subMenus);
				 this.getSubIndexMenu(subMenus, userInfoDto);
			 }
		 }
	 }

}