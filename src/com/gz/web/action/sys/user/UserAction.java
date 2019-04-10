package com.gz.web.action.sys.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gz.service.sys.user.UserService;
import com.gz.vo.login.User;
import com.gz.web.action.BaseAction;

@Controller
@RequestMapping("/gz/sys/user/userAction")
public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
	
	/**
	 * 查询user列表
	 * @param request
	 * @param length
	 * @param start
	 * @param columns
	 * @return
	 */
	@RequestMapping(value={"/user_list"})
	@ResponseBody
	public Map<String,Object> user_list(String rows, String page, String strquery) {
		try{
			int curPage = getCurPage(page);
			int pageSize = getPageSize(rows);
			Map<String, String> params = new HashMap<String, String>();
			if(strquery != null)params.put("strquery",decodeStrQuery(strquery));
			Map<String,Object> result = userService.user_list(params, curPage, pageSize);
			return result;
		}catch(Exception e){
			log.error("登录失败",e);
		}
		return null;
	}
	
	
	
	
	
	@RequestMapping(value={"/add"})
	@ResponseBody
	public String add(@RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile,String uploadpath ,User user) {
		try{
			String result = userService.add(uploadFile,user,uploadpath);
			return result;
		}catch(Exception e){
			log.error("添加用户失败",e);
			e.printStackTrace();
		}
		return "error";
	}
	@RequestMapping(value={"/update"})
	@ResponseBody
	public String update(@RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile,String uploadpath ,User user) {
		try{
			String result = userService.update(uploadFile,user,uploadpath);
			return result;
		}catch(Exception e){
			log.error("更新用户失败",e);
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value={"/user_del"})
	@ResponseBody
	public String delete_user(String[] userid) {
		try{
			String result = userService.delete_user(userid);
			return result;
		}catch(Exception e){
			log.error("添加用户失败",e);
			e.printStackTrace();
		}
		return "error";
	}
	
	
	@RequestMapping(value={"/queryById"})
	@ResponseBody
	@JsonDeserialize()
	public JSONObject queryById(String userid) {
		try{
			JSONObject result = userService.queryById(userid);
			return result;
		}catch(Exception e){
			log.error("获取用户信息失败",e);
		}
		return null;
	}
}
