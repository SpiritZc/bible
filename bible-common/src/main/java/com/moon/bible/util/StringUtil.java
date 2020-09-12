package com.moon.bible.util;

import com.moon.bible.enums.SqlOrderEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**  
 * @ClassName: StringUtil
 * @Description: 工具类
 * @author caiyang
 * @date 2020-05-29 11:25:12 
*/  
public class StringUtil {
	/**
	 * 判断字符串是否为空，null,空字符串，空格字符串都是返回true
	 * 
	 * @param str
	 * @return 是空，返回true，否则false
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否不为空，null,空字符串，空格字符串都是返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		return true;
	}
	
    protected static final String COMMA = ",";
	
    protected static final String orderTemplete = " `%s` %s ";
	/**  
	 * @Title: formatOrderSql
	 * @Description: 排序格式化
	 * @param sorts
	 * @return
	 * @author caiyang
	 * @date 2020-05-29 11:23:02 
	 */ 
	public static String formatOrderSql(Map<String, String> sorts)
	{
		if (CollectionUtils.isEmpty(sorts)) {
            return StringUtils.EMPTY;
        }
		List<String> sortSql = new ArrayList<>(1);
		Iterator<Map.Entry<String, String>> it = sorts.entrySet().iterator();
		while (it.hasNext()) {
			 Map.Entry<String, String> entry = it.next();
			 String value = entry.getValue().toLowerCase().trim();
	         String column = entry.getKey();
	         if (StringUtils.isEmpty(column)) {
	             continue;
	         }
	         if (StringUtils.equals(SqlOrderEnum.ASC.getCode(), value)) {
	             sortSql.add(String.format(orderTemplete, column, "asc"));
	         }
	         if (StringUtils.equals(SqlOrderEnum.DESC.getCode(), value)) {
	             sortSql.add(String.format(orderTemplete, column, "desc"));
	         }
		}
		return StringUtils.join(sortSql, COMMA);
	}
	
}