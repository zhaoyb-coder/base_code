package com.gz.web.action.sys.role;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gz.helper.WebAppHelper;
import com.gz.service.sys.role.RoleService;
import com.gz.vo.login.User;
import com.gz.web.action.BaseAction;

@Controller
@RequestMapping("/gz/sys/role/roleAction")
public class RoleAction extends BaseAction{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 查询user列表
	 * @param request
	 * @param length
	 * @param start
	 * @param columns
	 * @return
	 */
	@RequestMapping(value={"/role_list"})
	@ResponseBody
	public Map<String,Object> role_list(String rows, String page, String strquery) {
		try{
			int curPage = getCurPage(page);
			int pageSize = getPageSize(rows);
			Map<String, String> params = new HashMap<String, String>();
			if(strquery != null)params.put("strquery",decodeStrQuery(strquery));
			Map<String,Object> result = roleService.role_list(params, curPage, pageSize);
			return result;
		}catch(Exception e){
			log.error("查询角色列表失败",e);
		}
		return null;
	}
	
	
	
	
	
	@RequestMapping(value={"/add"})
	@ResponseBody
	public String add(@RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile,String uploadpath ,User user) {
		try{
			String result = roleService.add(uploadFile,user,uploadpath);
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
			String result = roleService.update(uploadFile,user,uploadpath);
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
			String result = roleService.delete_user(userid);
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
			JSONObject result = roleService.queryById(userid);
			return result;
		}catch(Exception e){
			log.error("获取用户信息失败",e);
		}
		return null;
	}
	@RequestMapping(value={"/queryMenuByUser"})
	@ResponseBody
	@JsonDeserialize()
	public JSONObject queryMenuByUser(HttpServletRequest request) {
		try{
			JSONObject result = roleService.queryMenuByUser(WebAppHelper.getLoginUserid(request));
			return result;
		}catch(Exception e){
			log.error("获取用户菜单信息失败",e);
		}
		return null;
	}
	
	@RequestMapping(value={"/queryMenu"})
	@ResponseBody
	@JsonDeserialize()
	public JSONArray queryMenu(String roleId) {
		try{
			JSONArray result = roleService.queryMenu(roleId);
			return result;
		}catch(Exception e){
			log.error("获取用户菜单信息失败",e);
		}
		return null;
	}
	
}
