package com.gz.helper.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gz.helper.StringHelper;


 
public class MapResultMapping extends Mapping {
	private Map<String,Object> map = new HashMap<String, Object>();
	
	
	public MapResultMapping(List<Map<String, Object>> list, String key, String value) {
		if(list != null) {
			for(Map<String, Object> result : list){
				map.put(result.get(key) + "", result.get(value));
			}
		}
	}
	 
	public MapResultMapping(List<Map<String, Object>> list) {
		this(list, "EVENTID", "MC");
	}
	


	@Override
	public Object map(String value) {
		if(value != null) {
			String[] values = value.split(",");
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < values.length; i++){
				if(i != 0)builder.append(",");
				builder.append(StringHelper.handleNull(map.get(values[i])));
			}
			return builder.toString();
		}
		return "";
	}

}
