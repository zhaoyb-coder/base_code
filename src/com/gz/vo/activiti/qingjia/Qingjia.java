package com.gz.vo.activiti.qingjia;

import java.util.Date;

public class Qingjia {
	private String eventid;
	private String task_time; //任务天数
	private String processInstanceId;//流程关联id
	private String userId; //任务发起人
	private String task_resonse; //任务说明
	private String task_type; //任务类型
	private String task_state; //任务状态
	private Date start_time; //任务开始时间
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getTask_time() {
		return task_time;
	}
	public void setTask_time(String task_time) {
		this.task_time = task_time;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTask_resonse() {
		return task_resonse;
	}
	public void setTask_resonse(String task_resonse) {
		this.task_resonse = task_resonse;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getTask_state() {
		return task_state;
	}
	public void setTask_state(String task_state) {
		this.task_state = task_state;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

}
