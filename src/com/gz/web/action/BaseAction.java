package com.gz.web.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gz.helper.DoeCodeHelper;
import com.gz.util.JsonObjectMapper;

/**
 * @ClassName: BaseAction
 * @Description:  
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB 
 * @date 2017-07-13  
 * @version V1.0
 */
public class BaseAction {
	public Log log = LogFactory.getLog(getClass());//日志记录log4j
	
	/**
	 *  
	 * @return
	 */
	public int getCurPage(String page){
		return Integer.parseInt((page == null || "0".equals(page)) ? "1":page);//当前页 
		}
	/**
	 * 数据转换
	 * @param map
	 * @return
	 * @throws JsonProcessingException 
	 */
	public JSONObject map2JSON(Map<String,Object> map) throws JsonProcessingException{
		JsonObjectMapper  objectMapper = new JsonObjectMapper ();
		String jsonMap = objectMapper.writeValueAsString(map).toString();
		return JSONObject.fromObject(jsonMap);
	}
	/**
	 * 数据转换
	 * @param map
	 * @return
	 * @throws JsonProcessingException 
	 */
	public JSONArray map2JSONArray(List<Map<String,Object>> li) throws JsonProcessingException{
		return JSONArray.parseArray(JSON.toJSONString(li));
	}
	
	/**
	 * 数据转换
	 * @param Object
	 * @return
	 */
	public JSONObject object2JSON(Object o){
		return JSONObject.fromObject(o);
	}
	
	/**
	 *  
	 * @return
	 */
	public int getPageSize(String rows){
		return Integer.parseInt((rows == null || "0".equals(rows)) ? "30":rows);//每页显示条数  
	}
	
	/**
	 * 
	 * @param flag
	 * @return
	 */
	public ResponseMsg responseMsg(String flag) {
		return new ResponseMsg(flag);
	}
	/**
	 *  
	 * @param flag
	 * @param msg
	 * @return
	 */
	public ResponseMsg responseMsg(String flag, String msg) {
		return new ResponseMsg(flag, msg);
	}
	
	/**
	 *  
	 * @param flag
	 * @param model
	 * @return
	 */
	public ResponseMsg responseMsg(String flag, Object model) {
		return new ResponseMsg(flag, model);
	}
	/**
	 *  
	 * @param flag
	 * @param result
	 * @return
	 */
	public ResponseMsg responseMsg(String flag, BindingResult result){
		if(result != null && result.hasErrors()){
			List<FieldError> fields = result.getFieldErrors();
			List<ModelMsg> list = new ArrayList<ModelMsg>();
			for(FieldError fe: fields) {
				ModelMsg mm = new ModelMsg(fe.getField(),fe.getDefaultMessage());
				list.add(mm);
			}
			return new ResponseMsg(flag, list);
		}
		return responseMsg(flag);
	}
	/**
	 * 搜索框数据格式化
	 * json格式的数据转换为 sql格式的数据
	 * @param strquery
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String decodeStrQuery(String strquery) throws UnsupportedEncodingException {
		String str = DoeCodeHelper.decodeStrQuery(strquery);
		Map<String,Object> m = JSON.parseObject(str); 
		StringBuilder strb = new StringBuilder();
		if(!m.isEmpty()){
			for(String key:m.keySet()){  
				if(m.get(key) != null && !m.get(key).toString().equals("")){
					String k = new String(m.get(key).toString().getBytes("iso-8859-1"),"utf-8");
					strb.append(" and "+key+"= '"+k+"'");
				}
	        }
		}
		return strb.toString();
	}
}
