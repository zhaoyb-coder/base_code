package com.gz.vo;

import java.util.Date;

public class FileInfo {
	
	private String id; 
	private String fileid;    
	private String realname; 	
	private String downpath;
	private String upload_user;    
	private Date create_time;
	private Date update_time;
	private String uploadpath;
	
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDownpath() {
		return downpath;
	}
	public void setDownpath(String downpath) {
		this.downpath = downpath;
	}
	public String getUpload_user() {
		return upload_user;
	}
	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
}
