package com.gz.service.login;

import static com.gz.common.StaticData.SUCCESS;
import static com.gz.common.StaticData.FAILER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gz.dao.BaseDao;
import com.gz.dao.login.LoginDao;
import com.gz.service.BaseService;
import com.gz.util.MD5;
import com.gz.vo.login.User;

@Service("loginService")
public class LoginService extends BaseService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public BaseDao getDao() {
		return loginDao;
	}
	
	public String login(User user) throws Exception{
		//1、通过用户名搜索数据库，判断用户是否存在
		List<Map<String,Object>> li = queryListMap("query_loginName", user.getLogin_name());
		if(li.size() > 0) {
			String md5_pwd = li.get(0).get("password").toString();
			String pwd = MD5.string2MD5(user.getPassword());
			if(md5_pwd.equals(pwd)){
				return "success";
			}else{
				return "密码错误";
			}
		}else{
			return "用户不存在";
		}
	}
	
	public JSONArray query_topMenu(HttpServletRequest request){
		//所有top_menu
		List<Map<String,Object>> all_List = query_allMenu(request,"query_menu", "1","0","0","0",null);
		
		//返回组合数据list
		List<Map<String,Object>> items_list = new ArrayList<Map<String,Object>>();
		
		if(all_List.size() == 0){
			Map<String,Object> menu_detail_map = new HashMap<String,Object>();
			menu_detail_map.put("classValue", "nav-item-inner nav-home");
			menu_detail_map.put("value", "首页");
			items_list.add(menu_detail_map);
		}
		
		//循环组合数据
		for(Map<String,Object> map : all_List){
			Map<String,Object> menu_detail_map = new HashMap<String,Object>();
			menu_detail_map.put("classValue", map.get("M_CLASS"));
			menu_detail_map.put("value", map.get("M_VALUE"));
			items_list.add(menu_detail_map);
		}
		
		return JSONArray.parseArray(JSON.toJSONString(items_list));
	}
	
	public JSONArray query_menu(HttpServletRequest request){
		int i = 1;
		//先获取所有top菜单，循环遍历从外到内组合数据
		//所有top_menu
		List<Map<String,Object>> all_List = query_allMenu(request,"query_menu", "1","0","0","0",null);
		
		//循环组合数据
		
		//定义最终返回list
		List<Map<String,Object>> result_List = new ArrayList<Map<String,Object>>();
		
		//默认构造首页
		Map<String,Object> code_map = new HashMap<String,Object>();
		
		if(all_List.size() == 0){
			//没有任何权限
			List<Map<String,Object>> levl1_List = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> test_list = this.add_codePage();
			code_map = code_page(test_list);//默认加载首页
			levl1_List.add(0,code_map);//加载首页（默认）,排在第一位
			Map<String,Object> level1_map = new HashMap<String,Object>();
			level1_map.put("menu", levl1_List);
			level1_map.put("id", "_"+i);
			level1_map.put("homePage", "code");
			result_List.add(level1_map);
		}
		
		for(Map<String,Object> map : all_List){//循环top菜单，每次循环得到一个最外层的map，就是每个top对应的所有lfet菜单
			//最外层map
			Map<String,Object> level1_map = new HashMap<String,Object>();
			List<Map<String,Object>> levl1_List = new ArrayList<Map<String,Object>>();
			
			level1_map.put("id", "_"+i);
			if(i == 1){
				level1_map.put("homePage", "code");
			}
			
			//现在需要找到top对应的所有left菜单列表
			//1---top菜单id
			String m_id = map.get("M_ID").toString();
			
			//2--通过id找到所属的第一层列表
			List<Map<String,Object>> level_query1_List = query_allMenu(request,"query_menu", "0","1","0","0",m_id);
			if(i == 1){
				List<Map<String,Object>> test_list = this.add_codePage();
				code_map = code_page(test_list);//默认加载首页
			}
			
			for(Map<String,Object> level_map : level_query1_List){//循环left菜单，根节点菜单
				//定义每一层的map
				Map<String,Object> level_menu_map = new HashMap<String,Object>();
				List<Map<String,Object>> levl2_List = new ArrayList<Map<String,Object>>();
				
				//先获取一层目录菜单名称
				level_menu_map.put("text", level_map.get("M_VALUE"));
				
				
				//获取二层目录列表
				List<Map<String,Object>> level_2_List = query_allMenu(request,"query_menu", "0","2",level_map.get("M_ID").toString(),level_map.get("M_ID").toString(),null);
				
				//增加首页权限（默认）
				//levl2_List.add(add_codePage());
				
				//循环二层目录列表
				for(Map<String,Object> level_map2 : level_2_List){
					Map<String,Object> level_2_map = new HashMap<String,Object>();
					level_2_map.put("text", level_map2.get("M_VALUE"));
					level_2_map.put("href", level_map2.get("M_URL"));
					
					//二层目录放在list
					levl2_List.add(level_2_map);
				}
				
				//wait...
				level_menu_map.put("items",levl2_List );
				
				levl1_List.add(level_menu_map);
				
			}
			if(i == 1){
				levl1_List.add(0,code_map);//加载首页（默认）,排在第一位
			}
			//wait...
			level1_map.put("menu", levl1_List);
			
			result_List.add(level1_map);
			i++;
		}
		
		return JSONArray.parseArray(JSON.toJSONString(result_List));
	}
	
	public List<Map<String,Object>> query_allMenu(HttpServletRequest request,String method,String m_left,String m_level,String m_pid,String m_tid ,String m_follow){
		Map<String,Object> params = new HashMap<String,Object>();
		String uid = request.getSession().getAttribute("now_user").toString();
		params.put("uuid", uid);
		if(m_left != null )params.put("m_left", m_left);
		if(m_level != null)params.put("m_level",m_level);
		if(m_pid != null )params.put("m_pid", m_pid);
		if(m_tid != null )params.put("m_tid", m_tid);
		if(m_follow != null )params.put("m_follow", m_follow);
		
		List<Map<String,Object>> all_List = queryListMap(method, params);
		
		return all_List;
	}
	
	/**
	 * 添加首页权限（默认）
	 * @return
	 */
	public List<Map<String,Object>> add_codePage(){
		Map<String,Object> menu_detail_map = new HashMap<String,Object>();
		menu_detail_map.put("id", "code");
		menu_detail_map.put("text", "首页");
		menu_detail_map.put("href", "test/welcome.jsp");
		menu_detail_map.put("closeable",false);
		List<Map<String,Object>> code_list = new ArrayList<Map<String,Object>>();
		code_list.add(menu_detail_map);
		return code_list;
	}
	/**
	 * (默认) 首页配置
	 * @return
	 */
	public Map<String,Object> code_page(List<Map<String,Object>> menu_list){
		//默认
		Map<String,Object> config_map = new HashMap<String,Object>();
		config_map.put("items", menu_list);
		config_map.put("text", "首页");
		return config_map;
	}


}
