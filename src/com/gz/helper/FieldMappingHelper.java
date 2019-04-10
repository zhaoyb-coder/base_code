package com.gz.helper;

import java.util.List;
import java.util.Map;

import com.gz.helper.mapping.Mapping;


public class FieldMappingHelper {
	
	/**
	 * @param sourceList 要处理的列表
	 * @param fieldName 列表中的字段名称,该字段的值需要处理
	 * @param mapping 映射map
	 */
	public static void process(List<Map<String,Object>> sourceList, String fieldName, Mapping mapping) {
		if(sourceList != null) {
			for(Map<String,Object> map : sourceList){
				process(map, fieldName, mapping);
			}
		}
	}
	/**
	 * 对单条记录进行映射处理
	 * @param map 单条记录
	 * @param fieldName字段名称,该字段的值需要处理
	 * @param mapping 映射map
	 */
	public static void process(Map<String,Object> map, String fieldName, Mapping mapping) {
		String value = map.get(fieldName) + "";  //获取到的值为：01,02,03
		if(!"null".equals(value) && !"".equals(value)){
			map.put(fieldName, mapping.map(value));
		}
	}
}
