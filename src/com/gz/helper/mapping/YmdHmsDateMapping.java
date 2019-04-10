package com.gz.helper.mapping;

public class YmdHmsDateMapping extends Mapping {
	@Override
	public Object map(String value) {
		if(value != null) {
			return value.substring(0, 19);
		}
		return "";
	}
}
