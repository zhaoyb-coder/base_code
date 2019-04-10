package com.gz.dao.sys.role;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("roleDao")
public class RoleDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.sys.role.RoleDao";
	}

}
