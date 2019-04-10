package com.gz.web.action;

import java.util.List;

/**
 * @ClassName: ResponseMsg
 * @Description: 返回前端的消息类
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB 
 * @date 2018-7-11 上午9:27:22
 * @version V1.0
 */
public class ResponseMsg {
	private String flag;  //成功或失败
	private String msg;  //成功或失败信息
	private Object model;  //可以返回数据
	private List<ModelMsg> mmg;  //校验失败的详细信息
	
	
	public ResponseMsg(String flag) {
		this.flag = flag;
	}

	public ResponseMsg(String flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}
	
	public ResponseMsg(String flag, Object model) {
		this.flag = flag;
		this.model = model;
	}

	public ResponseMsg(String flag, List<ModelMsg> mmg) {
		this.flag = flag;
		this.mmg = mmg;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * @return model
	 */
	public Object getModel() {
		return model;
	}

	/**
	 * @param model 
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	public List<ModelMsg> getMmg() {
		return mmg;
	}

	public void setMmg(List<ModelMsg> mmg) {
		this.mmg = mmg;
	}


}
//封装vo字段的错误信息
class ModelMsg {
	private String field;
	private String msg;
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public ModelMsg(String field, String msg) {
		this.field = field;
		this.msg = msg;
	}
}
