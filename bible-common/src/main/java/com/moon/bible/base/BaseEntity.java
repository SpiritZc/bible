package com.moon.bible.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.enums.MsgLevelEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**  
 * @ClassName: BaseEntity
 * @Description: 基底实体类
 * @author caiyang
 * @date 2020-05-29 10:56:13 
*/  
@Data
public class BaseEntity implements Serializable{

	/**
     * 	执行状态
     * 200:执行成功
     * 	其他:异常
     */
	@TableField(exist = false)
    private String statusCode = StatusCode.SUCCESS;

    /**
     * 	消息内容
     */
	@TableField(exist = false)
    private String statusMsg;
	
	/**
	 * 	消息级别
	 * info
	 * warn
	 * error
	 */
	@TableField(exist = false)
	private String msgLevel	= MsgLevelEnum.SUCCESS.getCode();
	
	/** 
	* @Fields reqSource : 请求来源
	*/
	@TableField(exist = false)
	private String reqSource;
	
	/** 
	* @Fields version : 版本号
	*/
	@TableField(exist = false)
	private String version;
	
	/**
     * 排序字符串
     * <p>在数据库的XML文件中，通过${}直接写入原文</p>
     */
	@TableField(exist = false)
    private String orderSql = StringUtils.EMPTY;

}
