package com.gz.dao.right;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("rightDao")
public class RightDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.right.RightDao";
	}
	
}
