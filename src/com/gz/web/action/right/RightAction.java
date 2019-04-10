package com.gz.web.action.right;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gz.service.right.RightService;
import com.gz.vo.login.User;
import com.gz.vo.right.Right;
import com.gz.web.action.BaseAction;

@Controller
@RequestMapping("/gz/right/rightAction")
public class RightAction extends BaseAction{
	
	@Autowired
	private RightService rightService;
	
	@RequestMapping(value={"/query_right"})
	@ResponseBody
	public JSONArray query_right(HttpServletRequest request,String roleId) {
		try{
			List<Map<String,Object>> li = rightService.query_right(request,roleId);
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	/**
	 * 获取模块列表
	 * @return
	 */
	@RequestMapping(value={"/query_modalList"})
	@ResponseBody
	public List<Map<String,Object>> query_modalList() {
		try{
			List<Map<String,Object>> li = rightService.queryTopList();
			return li;
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	/**
	 * 获取菜单组列表
	 * @return
	 */
	@RequestMapping(value={"/query_ProgList"})
	@ResponseBody
	public List<Map<String,Object>> query_ProgList() {
		try{
			List<Map<String,Object>> li = rightService.query_ProgList();
			return li;
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	@RequestMapping(value={"/addMenu"})
	@ResponseBody
	public String addMenu(Right right) {
		try{
			rightService.saveMenu(right);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			log.error("添加 菜单失败!");
			return "error";
		}
	}
	@RequestMapping(value={"/updateMenu"})
	@ResponseBody
	public String updateMenu(Right right) {
		try{
			rightService.updateMenu(right);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			log.error("添加 菜单失败!");
			return "error";
		}
	}
	
	@RequestMapping(value={"/queryTopList"})
	@ResponseBody
	public JSONArray queryTopList() {
		try{
			List<Map<String,Object>> li = rightService.queryTopList();
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	@RequestMapping(value={"/queryLeftOne"})
	@ResponseBody
	public JSONArray queryLeftOne(String topid) {
		try{
			List<Map<String,Object>> li = rightService.queryLeftOne(topid);
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	@RequestMapping(value={"/query_role"})
	@ResponseBody
	public JSONArray query_role(String uuid) {
		try{
			List<Map<String,Object>> li = rightService.query_role(uuid);
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	@RequestMapping(value={"/saveTreeNode"})
	@ResponseBody
	public JSONArray saveTreeNode(String roleid,HttpServletRequest request,String treeNodes) {
		try{
			List<Map<String,Object>> li = rightService.saveTreeNode(treeNodes,roleid);
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
	@RequestMapping(value={"/saveCheckNode"})
	@ResponseBody
	public JSONArray saveCheckNode(String uuid,String checkNode) {
		try{
			List<Map<String,Object>> li = rightService.saveCheckNode(checkNode,uuid);
			return map2JSONArray(li);
		}catch(Exception e){
			e.printStackTrace();
			log.error("查询角色所属菜单列表失败!");
			return null;
		}
	}
	
}
