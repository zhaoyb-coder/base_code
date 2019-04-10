package com.gz.vo.tool;

import java.util.Date;

public class ToolFile {
	private String fileid;    
	private String filename;    
	private String filepath; 	
	private String filetype;	
	private Date upload_date;
	private String file_codeName;    
	private String downpath;
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getFile_codeName() {
		return file_codeName;
	}
	public void setFile_codeName(String file_codeName) {
		this.file_codeName = file_codeName;
	}
	public String getDownpath() {
		return downpath;
	}
	public void setDownpath(String downpath) {
		this.downpath = downpath;
	}  
	
}
