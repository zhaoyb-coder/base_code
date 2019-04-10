package com.gz.helper;

import java.util.regex.Pattern;

import com.gz.common.StaticData;


public class StringHelper {
	public static final Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*[.]?[\\d]*$");  
	
	public static boolean isNumber(String str) {  
        return NUMBER_PATTERN.matcher(str).matches();  
    }
	
	public static boolean isDate(String str) {
		try {
            StaticData.YMD_FORMAT.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	
	
	public static boolean isTime(String str) {
		try {
			StaticData.YMDHMS_FORMAT.parse(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String handleNull(Object obj) {
		if(obj == null){
			return "";
		} else if("null".equalsIgnoreCase(obj.toString().trim())){
			return "";
		}
		return obj.toString();
	}
	public static void main(String[] args) {
		boolean b = StringHelper.isNumber("2345.1");
		System.out.println(b);
	}
	
}
