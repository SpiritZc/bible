
package com.moon.bible.log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moon.bible.annotation.MethodLog;
import com.moon.bible.api.operatelog.IOperateLogService;
import com.moon.bible.base.UserInfoDto;
import com.moon.bible.constants.Constants;
import com.moon.bible.entity.operatelog.OperateLog;
import com.moon.bible.enums.OperateStausEnum;
import com.moon.bible.enums.RequestHeaderEnums;
import com.moon.bible.util.CheckUtil;
import com.moon.bible.util.CusAccessObjectUtil;
import com.moon.bible.util.JWTUtil;
import com.moon.bible.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @Description 操作日志记录，添加、删除、修改、查询等方法的AOP
 * @author caiyang
 * @Date 2020年5月29日13:23:38
 */

@Component
@Aspect
public class LogAspect {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private IOperateLogService iOperateLogService;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	/**  
	 * @Fields reqSource : 默认请求来源
	 * @author caiyang
	 * @date 2020-06-12 06:09:34 
	 */ 
	@Value("${reqSource}")
	private Integer reqSource;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(com.moon.bible.annotation.MethodLog)")
	public void methodCachePointcut() {
	}

	@Around("methodCachePointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		String className = point.getThis().getClass().getName(); // 类名
		String ip = CusAccessObjectUtil.getIpAddress(httpServletRequest);
		if (className.indexOf("$$EnhancerBySpringCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
			try {
				className = className.substring(0, className.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		String methodName = className + "." + point.getSignature().getName(); // 获取方法名
		Object[] methodParam = null; // 参数
		Object object = null;
		String params = "";
		String simpleParams = "";
		//获取方法上的描述
		Map<String, String> methodMap = getMethodRemark(point);
		Integer reqSource = null;
		String authorization = httpServletRequest.getHeader(RequestHeaderEnums.Authorization.getCode());
		String headerReqSource = httpServletRequest.getHeader(RequestHeaderEnums.ReqSource.getCode());
		if (StringUtil.isNullOrEmpty(headerReqSource)) {
			reqSource = this.reqSource;
		}else {
			if(CheckUtil.isNumber(headerReqSource)) {
				reqSource = Integer.valueOf(headerReqSource);
			}else {
				reqSource = this.reqSource;
			}
		}
		String methodRemark = ""; // 操作备注
		String operateType = ""; // 操作类型
		String funModule = ""; // 功能模块
		String result = "";//返回参数
		long startTime = 0;
		long endTime = 0;
		if (methodMap != null) {
			funModule = methodMap.get("module");
			methodRemark = methodMap.get("remark"); // 获取操作备注
			operateType = methodMap.get("operateType"); // 获取操作类型
		}
		String currentUrl = httpServletRequest.getServletPath();//当前请求的url
		try {
			methodParam = point.getArgs(); // 获取方法参数
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			if (methodParam.length > 0) {
//				params = mapper.writeValueAsString(methodParam[0])+"-reqSource:"+headerReqSource;
				params = mapper.writeValueAsString(methodParam[0]);
			}else{
//				params = mapper.writeValueAsString(methodParam)+"-reqSource:"+headerReqSource;
				params = mapper.writeValueAsString(methodParam);
			}
			String requestType = httpServletRequest.getMethod();//获取请求类型
			if ("POST".equals(requestType)) {
				simpleParams = removePostEmptyArgs(params);
			}
			startTime=System.currentTimeMillis();   //获取开始时间
			String startLog = "请求" + currentUrl + "调用开始，全部参数：" + params+"-reqSource:"+headerReqSource;
			String simpleStartLog = "请求" + currentUrl + "调用开始，简洁参数：" + simpleParams+"-reqSource:"+headerReqSource;;
			logger.info(startLog);
			logger.info(simpleStartLog);
			object = point.proceed();
			result = JSONObject.toJSONString(object);
			endTime=System.currentTimeMillis(); //获取结束时间
			String endLog = "请求" + currentUrl + "调用结束，返回结果：" + result + "执行时长:" + (endTime-startTime) + "ms";
			logger.info(endLog);
			//存储到数据库
			OperateLog build = OperateLog.builder()
					.funModule(funModule)
					.operateType(operateType)
					.operateRemark(methodRemark)
					.operateMethod(methodName)
					.operateParams(params)
					.operateTime(new Date())
					.operateStatus(OperateStausEnum.SUCCESS.getCode())
					.errorInfo(endLog)
					.result(result)
					.operateIp(ip)
					.executeTime(String.valueOf((endTime - startTime)))
					.requestSource(reqSource)
					.build();
			if (!Constants.OPERATE_TYPE_SEARCH.equals(operateType) && applicationName.equals("bible-admin")) {
				//非查询类log并且是运营后台需要记录日志到db
				iOperateLogService.insert(build);
			}
			
		} catch (Exception e) {
			endTime=System.currentTimeMillis(); //获取结束时间
			String exceptionLog = "请求" + currentUrl + "调用结束，调用过程出现异常，返回结果：" + result + "，异常信息：" + e.getMessage() +"异常类型：" + e.getClass().toString() + "，执行时长:" + (endTime-startTime) + "ms";
			logger.info(exceptionLog);
			//操作失败插入log
			//存储到数据库
			OperateLog build = OperateLog.builder()
					.funModule(funModule)
					.operateType(operateType)
					.operateRemark(methodRemark)
					.operateMethod(methodName)
					.operateParams(params)
					.operateTime(new Date())
					.operateStatus(OperateStausEnum.FAILURE.getCode())
					.errorInfo(exceptionLog)
					.result(result)
					.operateIp(ip)
					.executeTime(String.valueOf((endTime - startTime)))
					.requestSource(reqSource)
					.build();
			if (!Constants.OPERATE_TYPE_SEARCH.equals(operateType) && applicationName.equals("bible-admin")) {
				//非查询类log并且是运营后台需要记录日志到db
				iOperateLogService.insert(build);
			}
			throw e;
		}
		return object;
	}

	
/**
	 * @Description 获取方法的备注，用于记录用户的操作日志描述
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */

	private static Map<String, String> getMethodRemark(JoinPoint joinPoint)
			throws Exception {
		Map<String, String> methodMap = new HashMap<String, String>();

		Class<?> targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		Object[] parameterTypes = joinPoint.getArgs();

		for (Method method : targetClass.getDeclaredMethods()) {
			if (method.getName().equals(methodName)
					&& method.getParameterTypes().length == parameterTypes.length) {
				MethodLog methodLog = method.getAnnotation(MethodLog.class);
				if (methodLog != null) {
					methodMap.put("module", methodLog.module());
					methodMap.put("remark", methodLog.remark());
					methodMap.put("operateType", methodLog.operateType());
					return methodMap;
				}
				break;
			}
		}

		return null;
	}
	
	//基底实体类中的属性
	public final static List<String> props = new ArrayList<String>(Arrays.asList("statusCode","statusMsg","msgLevel","orderSql","data","total"));
	/**  
	 * @Title: removePostEmptyArgs
	 * @Description: 移除post请求中为null或者""的参数
	 * @param params
	 * @return
	 * @author caiyang
	 * @throws JSONException 
	 * @date 2020-07-16 08:32:17 
	 */ 
	private String removePostEmptyArgs(String params) throws JSONException {
		Object object = JSON.parse(params);
		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) object;
			//移除基底实体类中无用的属性
			for (int i = 0; i < props.size(); i++) {
				jsonObject.remove(props.get(i));
			}
			//移除参数为null或者为""的属性
			Set<String> keys = jsonObject.keySet();
			List<String> list = new ArrayList<String>(keys);
			for (int i = 0; i < list.size(); i++) {
				if (jsonObject.get(list.get(i)) instanceof String) {
					if (jsonObject.get(list.get(i)) == null || StringUtil.isNullOrEmpty(jsonObject.get(list.get(i)).toString())) {
						jsonObject.remove(list.get(i));
					}
					}else {
						if (jsonObject.get(list.get(i)) == null) {
							jsonObject.remove(list.get(i));
						}
					}
			}
			return JSON.toJSONString(jsonObject);
		}else {
			return params;
		}
		
	}
}

