package com.gz.helper.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Mapping {
	/**
	 * 将数据库表格中的数据，取其中2列的值，转化成map数据。如阈值表的domain_name,code
	 * @param sourceList 列表，如：阈值表
	 * @param fieldKey list中某个字段，作为未来map中的key
	 * @param fieldValue list中某个字段，作为未来map中的value
	 * @return
	 */
	public Map<String,Object> list2Map(List<Map<String,Object>> sourceList, String fieldKey, String fieldValue){
		Map<String,Object> map = new HashMap<String, Object>();
		if(sourceList != null) {
			for(Map<String,Object> result : sourceList){
				map.put(result.get(fieldKey) + "", result.get(fieldValue));
			}
		}
		return map;
	}
	/**
	 * 01,02,03字段里的值，映射成对应的中文转油，脱水，注入等
	 * @param value 格式为：01,02,03
	 * @return
	 */
	public abstract Object map(String value);
}
