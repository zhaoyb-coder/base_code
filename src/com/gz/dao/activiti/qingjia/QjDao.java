package com.gz.dao.activiti.qingjia;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("qjDao")
public class QjDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.activiti.qingjia.QjDao";
	}
	
}
