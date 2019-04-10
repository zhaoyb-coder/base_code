package com.gz.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticMessage {
	public static String SUCCESS = null;  //成功标识位
	public static String FAILER = null;  //失败标识位
	public static String NONUNIQUENESS = null;  //该出厂编码已存在，请重新输入！
	
	
	static {
		ObjectMapper objectMapper = new ObjectMapper(); 
		Map<String, String> mapSuccess = new HashMap<String, String>(); 
		Map<String, String> mapFailer = new HashMap<String, String>(); 
		Map<String, String> mapNonuniqueness = new HashMap<String, String>(); 
		mapSuccess.put("result", "true");  
		mapSuccess.put("message", "保存成功");
		mapFailer.put("result", "false");  
		mapFailer.put("message", "保存失败");
		mapNonuniqueness.put("result", "false");  
		mapNonuniqueness.put("message", "该出厂编码已存在，请重新输入！");
		try {
			SUCCESS = objectMapper.writeValueAsString(mapSuccess);
			FAILER = objectMapper.writeValueAsString(mapFailer);
			NONUNIQUENESS = objectMapper.writeValueAsString(mapNonuniqueness);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	};
	
	public static void main(String[] args) {  
        System.out.println(StaticMessage.SUCCESS);
        System.out.println(StaticMessage.FAILER);
        System.out.println(StaticMessage.NONUNIQUENESS);
    }  
}
