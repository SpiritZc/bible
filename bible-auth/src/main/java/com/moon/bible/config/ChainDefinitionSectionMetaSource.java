package com.moon.bible.config;

import com.moon.bible.api.sysfunction.ISysFunctionService;
import com.moon.bible.entity.sysfunction.SysFunction;
import com.moon.bible.constants.Constants;
import com.moon.bible.enums.ApplicationNameEnum;
import com.moon.bible.enums.FunctionRuleEnum;
import com.moon.bible.enums.FunctionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**  
 * @ClassName: ChainDefinitionSectionMetaSource
 * @Description: 动态获取权限资源
 * @author caiyang
 * @date 2020-06-08 11:38:45 
*/
public class ChainDefinitionSectionMetaSource{

	/**
	 * @Fields iSysFunctionService : 功能service注入
	 * @author caiyang
	 * @date 2020-06-08 08:43:13 
	 */
	@Autowired
	@Lazy
	private ISysFunctionService iSysFunctionService;

	@Value("${spring.application.name}")
	public String applicationName;
	
	
	public Map<String, String> getObject() throws Exception {
		Map<String, String> section = new LinkedHashMap<String, String>();
		//获取所有的功能url
		List<SysFunction> list = new ArrayList<>();
		if (ApplicationNameEnum.BIBLEADMIN.getCode().equals(applicationName)){
			list = iSysFunctionService.getAllFunctionsByType(FunctionTypeEnum.ADMIN.getCode());
		}else {
			list = iSysFunctionService.getAllFunctionsByType(FunctionTypeEnum.APPLETS.getCode());
		}
		if (list != null && list.size() > 0) {
			for (SysFunction sysFunction : list) {
					//如果是运营后台
				if (sysFunction.getFunctionType() == FunctionTypeEnum.ADMIN.getCode()){
					if (FunctionRuleEnum.PUB.getCode()==sysFunction.getRule()) {
						//公开访问，不需要鉴权
						section.put(sysFunction.getFunctionUrl(), Constants.PUBLIC_STRING);
					}else if (FunctionRuleEnum.JWT.getCode() == sysFunction.getRule()) {
						//只需校验登录token
						section.put(sysFunction.getFunctionUrl(), Constants.JWT_STRING);
					}else {
						//平台功能接口
						section.put(sysFunction.getFunctionUrl(),
								MessageFormat.format(Constants.PC_ADMIN_PREMISSION_STRING, sysFunction.getFunctionCode()));
					}
				}else {
					//如果是小程序端
					if (FunctionRuleEnum.PUB.getCode()==sysFunction.getRule()) {
						//公开访问，不需要鉴权
						section.put(sysFunction.getFunctionUrl(), Constants.PUBLIC_STRING);
					}else if (FunctionRuleEnum.JWT.getCode() == sysFunction.getRule()) {
						//只需校验登录token
						section.put(sysFunction.getFunctionUrl(), Constants.JWT_APPLETS_STRING);
					}else {
						//平台功能接口
						section.put(sysFunction.getFunctionUrl(),
								MessageFormat.format(Constants.APPLETS_PREMISSION_STRING, sysFunction.getFunctionCode()));
					}
				}

			}
		}
//		section.put("/api/sysOrg/getTableList", JWT_STRING);
        return section;
	}

}
