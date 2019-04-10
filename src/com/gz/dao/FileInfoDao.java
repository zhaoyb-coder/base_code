package com.gz.dao;

import org.springframework.stereotype.Repository;

@Repository("fileInfoDao")
public class FileInfoDao extends BaseDao{

	@Override
	public String getNameSpace() {
		return "com.gz.dao.FileInfoDao";
	}
	
}
