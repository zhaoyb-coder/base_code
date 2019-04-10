package com.gz.vo.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 阈值表
 * @author zhaoyubo 
 *
 */
public class SysDomain implements Serializable {
	private static final long serialVersionUID = -2075034220670222874L;
	
	private String domain_id;
	private String domain_name;
	private String domain_code;
	private String domain_description;
	private int domain_order;
	private String domain_level;
	private Date creat_time;
	private String creat_userid;
	private Date update_time;
	private String update_userid;
	public String getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getDomain_code() {
		return domain_code;
	}
	public void setDomain_code(String domain_code) {
		this.domain_code = domain_code;
	}
	public String getDomain_description() {
		return domain_description;
	}
	public void setDomain_description(String domain_description) {
		this.domain_description = domain_description;
	}
	public int getDomain_order() {
		return domain_order;
	}
	public void setDomain_order(int domain_order) {
		this.domain_order = domain_order;
	}
	public String getDomain_level() {
		return domain_level;
	}
	public void setDomain_level(String domain_level) {
		this.domain_level = domain_level;
	}
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
	public String getCreat_userid() {
		return creat_userid;
	}
	public void setCreat_userid(String creat_userid) {
		this.creat_userid = creat_userid;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_userid() {
		return update_userid;
	}
	public void setUpdate_userid(String update_userid) {
		this.update_userid = update_userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
