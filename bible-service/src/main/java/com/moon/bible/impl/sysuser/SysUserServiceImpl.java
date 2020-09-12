package com.moon.bible.impl.sysuser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.Constants;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.dto.sysuser.SysUseDto;
import com.moon.bible.dto.sysuser.UserWithRoleDto;
import com.moon.bible.entity.sysuser.SysUser;
import com.moon.bible.entity.sysuserrole.SysUserRole;
import com.moon.bible.exception.BizException;
import com.moon.bible.mapper.sysuser.SysUserMapper;
import com.moon.bible.api.sysuser.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.PageEntity;
import com.moon.bible.enums.DelFlagEnum;
import com.moon.bible.mapper.sysuserrole.SysUserRoleMapper;
import com.moon.bible.util.Md5Util;
import com.moon.bible.util.MessageUtil;
import com.moon.bible.util.StringUtil;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SysUser服务实现
* @author 
* @date 2020-07-14 07:28:39
* @version V1.0  
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Resource
	private SysUserRoleMapper sysUserRoleMapper;
  
	/** 
	* @Title: tablePagingQuery 
	* @Description: 表格分页查询
	* @param @param model
	* @return BaseEntity 
	* @author 
	* @throws 
	*/ 
	@Override
	public PageEntity tablePagingQuery(SysUser model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<SysUser> list = this.baseMapper.searchDataLike(model);
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
		return this.baseMapper.getDetail(id);
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
	public BaseEntity insert(UserWithRoleDto model) {
		if (this.checkRepeat(new QueryWrapper<SysUser>().eq("account_name", model.getAccountName())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"账户名"}));
		}
		if (this.checkRepeat(new QueryWrapper<SysUser>().eq("user_phone", model.getUserPhone())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"手机号"}));
		}
		if (StringUtil.isNullOrEmpty(model.getPassword())){
			model.setPassword(Md5Util.generateMd5(Constants.DEFAULT_PASSWORD));
		}else {
			model.setPassword(Md5Util.generateMd5(model.getPassword()));
		}
		BaseEntity result = new BaseEntity();
		//保存用户
		this.save(model);
		//保存用户角色关系
		SysUserRole userAccountUserRole = new SysUserRole();
		userAccountUserRole.setUserId(model.getId());
		userAccountUserRole.setRoleId(model.getRoleId());
		sysUserRoleMapper.insert(userAccountUserRole);
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
	public BaseEntity update(UserWithRoleDto model) {
		if (this.checkRepeat(new QueryWrapper<SysUser>().eq("account_name", model.getAccountName())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode())
				.ne("id",model.getId()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"账户名"}));
		}
		if (this.checkRepeat(new QueryWrapper<SysUser>().eq("user_phone", model.getUserPhone())
				.eq("del_flag",DelFlagEnum.UNDEL.getCode())
				.ne("id",model.getId()),q->this.baseMapper.selectCount(q) != 0)){
			throw new BizException(StatusCode.CHECK_FAILURE,MessageUtil.getValue("error.check.exist",new String[]{"手机号"}));
		}
//		model.setUserPwd(Md5Util.generateMd5(model.getUserPwd()));
		BaseEntity result = new BaseEntity();
		//更新用户
		this.updateById(model);
		//更新用户和角色关系
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(model.getRoleId());
		sysUserRoleMapper.update(sysUserRole,new UpdateWrapper<SysUserRole>().eq("user_id",model.getId()));
		result.setStatusMsg(MessageUtil.getValue("info.update"));
		return result;
	}

	/**
	*<p>Title: delete</p>
	*<p>Description: 单条删除数据</p>
	* @author 
	* @param id
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity delete(Long id) {
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		sysUser.setDelFlag(DelFlagEnum.DEL.getCode());
		this.updateById(sysUser);
		BaseEntity result = new BaseEntity();
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	/**
	*<p>Title: deleteBatch</p>
	*<p>Description: 批量删除数据</p>
	* @author 
	* @param ids
	* @return
	*/
	@Transactional
	@Override
	public BaseEntity deleteBatch(List<Long> ids) {
		List<SysUser> list = new ArrayList<SysUser>();
		for (int i = 0; i < ids.size(); i++) {
			SysUser sysUser = new SysUser();
			sysUser.setId(ids.get(i));
			sysUser.setDelFlag(DelFlagEnum.DEL.getCode());
			list.add(sysUser);
		}
		BaseEntity result = new BaseEntity();
		if (list != null && list.size() > 0) {
			this.updateBatchById(list);
		}
		result.setStatusMsg(MessageUtil.getValue("info.delete"));
		return result;
	}

	 /**
	  * @Title: changePwd
	  * @Description: 修改密码
	  * @param sysUseDto
	  * @param userInfoDto
	  * @return
	  * @author caiyang
	  * @date 2020-06-12 01:31:45
	  */
	 @Override
	 public BaseEntity changePwd(SysUseDto sysUseDto, UserInfoDto userInfoDto) {
		 BaseEntity result = new BaseEntity();
		 //获取当前用户信息
		 SysUser sysUser = this.getById(userInfoDto.getUserId());
		 //判断旧密码是否输入正确
		 if (!Md5Util.generateMd5(sysUseDto.getOldPwd()).equals(sysUser.getPassword())) {
			 throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.oldpwd"));
		 }
		 sysUser = new SysUser();
		 sysUser.setPassword(Md5Util.generateMd5(sysUseDto.getNewPwd()));
		 sysUser.setId(userInfoDto.getUserId());
		 this.updateById(sysUser);
		 result.setStatusMsg(MessageUtil.getValue("info.changepwd"));
		 return result;
	 }

	@Override
	public BaseEntity tablePagingQueryByAuthority(SysUser model) {
		PageEntity result = new PageEntity();
		model.setDelFlag(DelFlagEnum.UNDEL.getCode());
		com.github.pagehelper.Page<?> page = PageHelper.startPage(model.getCurrentPage(), model.getPageSize()); //分页条件
		List<UserWithRoleDto> userWithOrgDtos = this.baseMapper.tablePagingQueryByAuthority(model);
		result.setData(userWithOrgDtos);
		result.setTotal(page.getTotal());
		result.setCurrentPage(model.getCurrentPage());
		result.setPageSize(model.getPageSize());
		return result;
	}

	/**
	 * @Method resetPwd
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 根据用户id重置密码
	 * @Return com.baiyyy.yfz.base.BaseEntity
	 * @Exception
	 * @Date 2020-6-12 9:18
	 */
	@Override
	public BaseEntity resetPwd(Long id) {
		BaseEntity result = new BaseEntity();
		SysUser userAccount = new SysUser();
		userAccount.setId(id);
		userAccount.setPassword(Md5Util.generateMd5(Constants.DEFAULT_PASSWORD));
		this.baseMapper.updateById(userAccount);
		result.setStatusMsg(MessageUtil.getValue("info.reset.password"));
		return result;
	}

	/**
	 * @Method updateStatus
	 * @Author zhangcheng
	 * @Version  1.0
	 * @Description 启用停用
	 * @Return
	 * @Exception
	 * @Date 2020-7-24 10:20
	 */
	@Override
	public BaseEntity updateStatus(SysUser sysUser) {
		BaseEntity result = new BaseEntity();
		SysUser param = new SysUser();
		param.setId(sysUser.getId());
		param.setStatus(sysUser.getStatus());
		this.updateById(param);
		result.setStatusMsg(MessageUtil.getValue("info.operation"));
		return result;
	}

	/**
	    * 功能描述: 根据角色id查询用户列表<br>
	    * 〈〉
	    * @Param: [roleId]
	    * @Return: java.util.List<com.moon.bible.entity.sysuser.SysUser>
	    * @Author: Administrator
	    * @Date: 2020/8/29 21:17
	 */
	@Override
	public List<SysUser> getUserByRoleId(String roleId) {
		List<SysUser> users = this.baseMapper.getUserByRoleId(roleId);
		return  users;
	}


//	 public static void main(String[] args) {
//		 System.out.println(Md5Util.generateMd5("123456"));
//	 }
}