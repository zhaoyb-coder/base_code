package com.gz.helper.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gz.helper.StringHelper;
import com.gz.vo.common.SysDomain;


public class DomainFieldMapping extends Mapping {
	private Map<String,Object> map = new HashMap<String, Object>();
			
	public DomainFieldMapping(List<SysDomain> mappingList) {
		if(mappingList != null) {
			for(SysDomain result : mappingList){
				map.put(result.getDomain_code(), result.getDomain_description());
			}
		}
	}

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
