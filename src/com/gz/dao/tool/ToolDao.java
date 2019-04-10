package com.gz.dao.tool;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("toolDao")
public class ToolDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.tool.ToolDao";
	}


}
