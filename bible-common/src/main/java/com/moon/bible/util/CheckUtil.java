package com.moon.bible.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

	/**
	 * 是否不为空
	 * 
	 * @param value    字段值
	 * @param msgParam 错误消息参数，其中包括一些判断条件，例如长度
	 * @return 是否不为空
	 */
	public static Boolean isNotNull(Object value) {
		Boolean isNotNull = Boolean.TRUE;
		Boolean isStringNull = (value instanceof String) && StringUtil.isNullOrEmpty((String) value);
		Boolean isCollectionNull = (value instanceof Collection) && CollectionUtils.isEmpty((Collection) value);
		if (value == null) {
			isNotNull = Boolean.FALSE;
		} else if (isStringNull || isCollectionNull) {
			isNotNull = Boolean.FALSE;
		}
		return isNotNull;
	}

	/**
	 * 数字校验
	 * 
	 * @param number 待校验数字
	 * @param intMax 最大整数位
	 * @param decMax 最大小数位(没有小数位写0)
	 * @return 校验结果 满足true 不满足false
	 */
	public static boolean isNumber(String number, int intMax, int decMax) {
		boolean flag = false;
		if (StringUtils.isEmpty(number)) {
			flag = true;
		} else if (decMax == 0) {
			flag = number.matches("^[0-9]+$") && number.length() <= intMax;
		} else {
			String[] num = number.split("\\.");
			if (num.length < 2) {
				flag = number.matches("^[0-9]+\\.{0,1}[0-9]*") && num[0].length() <= intMax;
			} else {
				flag = number.matches("^[0-9]+\\.{0,1}[0-9]*") && num[0].length() <= intMax
						&& num[1].length() <= decMax;
			}
		}
		return flag;
	}

	/**
	 * 日期校验
	 * 
	 * @param str    待校验日期
	 * @param format 日期格式
	 * @return 校验结果 满足true 不满足false
	 */
	public static boolean isDate(String str, String format) {
		boolean flag = false;
		if (StringUtils.isEmpty(str)) {
			flag = true;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				sdf.setLenient(false);
				sdf.parse(str);
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 电话校验
	 * 
	 * @param tel 待校验电话
	 * @return 校验结果 满足true 不满足false
	 */
	public static boolean isTel(String tel) {
		boolean flag = false;
		if (StringUtils.isEmpty(tel)) {
			flag = true;
		} else {
			flag = tel.matches("^[0-9-+\\(\\) ]*$");
		}

		return flag;
	}

	/**
	 * 邮箱校验
	 * 
	 * @param mail 待校验邮箱
	 * @return 校验结果 满足true 不满足false
	 */
	public static boolean isMail(String mail) {
		boolean flag = false;
		if (StringUtils.isEmpty(mail)) {
			flag = true;
		} else {
			flag = mail.matches("^([_a-zA-Z0-9-]+)(\\.[_a-zA-Z0-9-]+)*@([_a-zA-Z0-9-]+\\.)+([a-zA-Z]{2,3})$");
		}

		return flag;
	}

	/**
	 * @Description: 判断是否是手机号 @param @param mobiles @param @return @return
	 * boolean @throws
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**  
	 * @Title: isMobileOrPhone
	 * @Description: 判断是否是座机或者手机号
	 * @param mobilephone
	 * @return
	 * @author caiyang
	 * @date 2020-06-18 03:20:59 
	 */ 
	public static boolean isMobileOrPhone(String mobilephone) {
		Pattern p = Pattern.compile("^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})$|(1[0-9])\\d{9}$");
		Matcher m = p.matcher(mobilephone);
		return m.matches();
	}
	
	/**
	* @Title: validStrLength
	* @Description: 判断字符串长度
	* @param @param str
	* @param @param length
	* @param @return 参数
	* @return boolean
	* @throws
	*/
	public static boolean validStrLength(String str,Integer length)
	{
		if (StringUtil.isNullOrEmpty(str)) {
			return Boolean.TRUE;
		}
		if (str.length() > length) {
			return Boolean.FALSE;
		}else {
			return Boolean.TRUE;
		}
	}
	
	/**
	*<p>Title: validMax</p>
	*<p>Description: 比较大小</p>
	* @author caiyang
	* @param value
	* @param max
	* @return
	*/
	public static boolean validMax(BigDecimal value,BigDecimal max)
	{
		if (value.compareTo(max)>0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	* @Title: isNumeric
	* @Description: 判断字符串是否为数字
	* @param @param str
	* @param @return 参数
	* @return boolean
	* @throws
	*/
	public static boolean isNumeric(String str)
	{
		 // 该正则表达式可以匹配所有的数字 包括负数
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
		String bigStr;
		try {
		bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
		return false;//异常 说明包含非数字。
		}

		Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	 /**
     * 判断一个字符串是否是数字。
     * 
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
	
	public static boolean isEmail(String str)
	{
		if (StringUtil.isNullOrEmpty(str)) {
			return true;
		}
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		if (str.matches(regex)) {
			return true;
		}else {
			return false;
		}
	}
}
