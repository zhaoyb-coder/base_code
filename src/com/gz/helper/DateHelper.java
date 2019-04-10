package com.gz.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期帮助类
 * 日期-->String 相互转化
 * @author Administrator
 *
 */
public class DateHelper {

	/**
	 * 日期类型转化为字符串
	 * format 传需要转换的格式 如yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转化为日期类型
	 * format 传 字符串的格式 如yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date String2Date(String str,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
