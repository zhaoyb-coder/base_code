package com.gz.dao.sys.user;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("userDao")
public class UserDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.sys.user.UserDao";
	}

}
