package com.gz.dao.login;

import org.springframework.stereotype.Repository;

import com.gz.dao.BaseDao;

@Repository("loginDao")
public class LoginDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.login.LoginDao";
	}

}
