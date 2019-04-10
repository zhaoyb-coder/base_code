package com.gz.dao.common;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;


@Repository("sysDomainDao")
public class SysDomainDao extends BaseDao{
	@Override
	public String getNameSpace() {
		return "com.gz.dao.common.SysDomainDao";
	}
}
