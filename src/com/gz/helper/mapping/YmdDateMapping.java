package com.gz.helper.mapping;


 
public class YmdDateMapping extends Mapping {
	
	@Override
	public Object map(String value) {
		if(value != null) {
			return value.substring(0, 10);
		}
		return "";
	}

}
