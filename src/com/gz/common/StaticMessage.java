package com.gz.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticMessage {
	public static String SUCCESS = null;  //�ɹ���ʶλ
	public static String FAILER = null;  //ʧ�ܱ�ʶλ
	public static String NONUNIQUENESS = null;  //�ó��������Ѵ��ڣ����������룡
	
	
	static {
		ObjectMapper objectMapper = new ObjectMapper(); 
		Map<String, String> mapSuccess = new HashMap<String, String>(); 
		Map<String, String> mapFailer = new HashMap<String, String>(); 
		Map<String, String> mapNonuniqueness = new HashMap<String, String>(); 
		mapSuccess.put("result", "true");  
		mapSuccess.put("message", "����ɹ�");
		mapFailer.put("result", "false");  
		mapFailer.put("message", "����ʧ��");
		mapNonuniqueness.put("result", "false");  
		mapNonuniqueness.put("message", "�ó��������Ѵ��ڣ����������룡");
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
