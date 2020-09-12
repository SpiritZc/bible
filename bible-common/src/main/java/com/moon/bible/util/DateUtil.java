package com.moon.bible.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**  
 * @ClassName: DataUtils
 * @Description:日期工具类
 * @author caiyang
 * @date 2020-05-29 01:29:50 
*/  
public class DateUtil {

	/**
     * 预设不同的时间格式
     */
    //精确到年月日（英文） eg:2019-12-31
    public static String FORMAT_LONOGRAM = "yyyy-MM-dd";
    //精确到时分秒的完整时间（英文） eg:2010-11-11 12:12:12
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    //精确到毫秒完整时间（英文） eg:2019-11-11 12:12:12.55
    public static String FORMAT_LONOGRAM_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
    //精确到年月日（中文）eg:2019年11月11日
    public static String FORMAT_LONOGRAM_CN = "yyyy年MM月dd日";
    //精确到时分秒的完整时间（中文）eg:2019年11月11日 12时12分12秒
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时MM分SS秒";
    //精确到毫秒完整时间（中文）
    public static String FORMAT_LONOGRAM_MILL_CN = "yyyy年MM月dd日HH时MM分SS秒SSS毫秒";
	
	 /**  
	 * @Fields format : 默认日期格式
	 * @author caiyang
	 * @date 2020-05-29 01:44:37 
	 */ 
	private static final SimpleDateFormat format = new SimpleDateFormat(FORMAT_FULL);
	
	 /**  
	 * @Title: date2String
	 * @Description: 日期转换成string，采用末日的日期格式
	 * @param date
	 * @return
	 * @author caiyang
	 * @date 2020-05-29 01:46:48 
	 */ 
	public static String date2String(Date date) {
		 return format.format(date);
	 }
	
    /**  
     * @Title: date2String
     * @Description: 日期转换成string，自定义日期格式
     * @param date
     * @param dateFormat
     * @return
     * @author caiyang
     * @date 2020-05-29 01:47:16 
     */ 
    public static String date2String(Date date,String dateFormat) {
    	if (StringUtil.isNullOrEmpty(dateFormat)) {
			return format.format(date);
		}
    	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        return sdf.format(date);
    }
    
    /**  
     * @Title: string2Date
     * @Description: 字符串转日期，采用默认日期格式
     * @param date
     * @return
     * @author caiyang
     * @date 2020-05-29 01:50:54 
     */ 
    public static Date string2Date(String date) {
    	 try {
             return format.parse(date);
         } catch (Exception e) {
             e.printStackTrace();
         }

         return null;
    }
    
    /**  
     * @Title: string2Date
     * @Description: 字符串转日期，自定义日期格式
     * @param date
     * @param dateFormat
     * @return
     * @author caiyang
     * @date 2020-05-29 01:50:27 
     */ 
    public static Date string2Date(String date, String dateFormat) {
    	try {
    		if (StringUtil.isNullOrEmpty(dateFormat)) {
        		return format.parse(date);
        	}
        	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        	return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**  
     * @Title: getNow
     * @Description: 返回当前时间字符串，默认格式
     * @return
     * @author caiyang
     * @date 2020-05-29 01:56:59 
     */ 
    public static String getNow() {
        return date2String(new Date());
    }
    
    /**  
     * @Title: getNow
     * @Description: 返回当前时间字符串，自定义格式
     * @param format
     * @return
     * @author caiyang
     * @date 2020-05-29 01:57:34 
     */ 
    public static String getNow(String format) {
        return date2String(new Date(),format);
    }
    
    /**  
     * @Title: getTimeStamp
     * @Description: 获取时间戳
     * @return
     * @author caiyang
     * @date 2020-05-29 01:59:41 
     */ 
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONOGRAM_MILL);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
    
    /**  
     * @Title: getYear
     * @Description: 获取日期的年份
     * @param date
     * @return
     * @author caiyang
     * @date 2020-05-29 02:01:38 
     */ 
    public static String getYear(Date date) {
        return date2String(date).substring(0,4);
    }
    
    /**  
     * @Title: getYearMonth
     * @Description: 获取日期的年份+月份
     * @param date
     * @return
     * @author caiyang
     * @date 2020-05-29 02:01:47 
     */ 
    public static String getYearMonth(Date date) {
        return date2String(date).substring(0, 7);
    }
    
    /**  
     * @Title: getHour
     * @Description: 获取日期的小时数
     * @param date
     * @return
     * @author caiyang
     * @date 2020-05-29 02:02:01 
     */ 
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    /**  
     * @Title: Stamp2String
     * @Description: 时间戳转化为字符串，自定义格式
     * @param stamp
     * @param format
     * @return
     * @author caiyang
     * @date 2020-05-29 02:02:16 
     */ 
    public static String Stamp2String(Long stamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(stamp));
    }

    /**
     * 时间戳转化为字符串
     *
     * @param stamp 时间戳
     * @return 字符串
     */
    /**  
     * @Title: Stamp2String
     * @Description: 时间戳转化为字符串，默认格式
     * @param stamp
     * @return
     * @author caiyang
     * @date 2020-05-29 02:02:31 
     */ 
    public static String Stamp2String(Long stamp) {
        return format.format(new Date(stamp));
    }
    
	/**  
	 * @Title: getTimestampLong
	 * @Description: 获取long类型的时间戳
	 * @return
	 * @author caiyang
	 * @date 2020-06-08 04:59:53 
	 */ 
	public static Long getTimestampLong(){
		return Long.valueOf(System.currentTimeMillis());
	}
    
    public static void main(String[] args) {
    	
      
    }
}
