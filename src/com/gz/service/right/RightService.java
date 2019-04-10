package com.gz.service.right;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gz.dao.BaseDao;
import com.gz.dao.right.RightDao;
import com.gz.helper.WebAppHelper;
import com.gz.service.BaseService;
import com.gz.vo.right.Right;


@Service("rightService")
public class RightService extends BaseService{

	@Autowired
	private RightDao rightDao;
	
	@Override
	public BaseDao getDao() {
		return rightDao;
	}
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public void saveMenu(Right right) throws Exception{
		//主键
		right.setId(WebAppHelper.newEventId());
		right.setCreate_time(new Date());
		
		rightDao.insert("saveMenu", right);
		
	}
	public void updateMenu(Right right) throws Exception{
		//主键
		right.setUpdate_time(new Date());
		
		rightDao.update("updateMenu", right);
		
	}
	/**
	 * 查询列表
	 * @param pageSize
	 * @param curPage
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> query_list(int pageSize,int curPage,String search) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();//参数
		Map<String,Object> jsonMap = listData2PageData("query_list",params,  curPage , pageSize );//数据
		return jsonMap;
	}
	/**
	 * 查询列表
	 * @param pageSize
	 * @param curPage
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> query_menulist(int pageSize,int curPage,String search) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();//参数
		Map<String,Object> jsonMap = listData2PageData("query_menulist",params,  curPage , pageSize );//数据
		return jsonMap;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> query_right(HttpServletRequest request,String roleid) throws Exception{
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		//获取用户uesrid
		String userid = WebAppHelper.getLoginUserid(request);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid",userid);
		params.put("level","1");
		
		//获取此用户所有一级菜单
		List<Map<String,Object>> level1List = queryListMap("query_right",params);
		
		//循环一级菜单，给每个一级菜单查找下一级菜单 children[{},{}]
		for(Map<String,Object> map : level1List){
			Map<String,Object> menu_map = new HashMap<String,Object>();
			if(map == null || map.isEmpty()) continue;
			menu_map.put("id",map.get("id"));//系统管理id
			//查找二级菜单
			params.put("level","2");
			params.put("pid",map.get("id"));
			List<Map<String,Object>> level2List = queryListMap("query_right",params);
			
			//下级菜单
			for(Map<String,Object> map2 : level2List){
				params.put("level","3");
				params.put("pid",map2.get("id"));
				List<Map<String,Object>> level3List = queryListMap("query_right",params);
				
				map2.put("children", level3List);
			}
			map.put("children", level2List);
			resultList.add(map);
		}
		System.out.println(resultList);
		return resultList;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryTopList() throws Exception{
		return queryListMap("queryTopList",null);
	}
	
	public List<Map<String,Object>> queryLeftOne( String topid) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("topid",topid);
		return queryListMap("queryLeftOne",params);
	}
	
	public List<Map<String,Object>> query_ProgList() throws Exception{
		return queryListMap("queryLeft",null);
	}
	
	public List<Map<String,Object>> query_role(String uuid) throws Exception{
		return queryListMap("query_role",uuid);
	}
	
	
	public List<Map<String,Object>> saveTreeNode(String treeNodes,String roleid){
		String[] tns = treeNodes.split(",");
		
		//先删除 此角色的所有权限
		Map<String,Object> params1 = new HashMap<String,Object>();
		params1.put("roleId",roleid);
		List<Map<String,Object>> query_menu_role = queryListMap("query_rid",params1);
		if(query_menu_role.size() > 0){
			//此角色当前有权限菜单
			params1.put("rightId",query_menu_role.get(0).get("RIGHT_ID").toString());
			
			rightDao.delete("deleteRightInfoById", params1);
			rightDao.delete("deleteRolRightById", params1);
		}
		
		//再给角色添加页面配置的权限
		String rid = WebAppHelper.newEventId();
		for(String tn : tns){
			String uuids = WebAppHelper.newEventId();
			params1.put("uuid",uuids);
			params1.put("r_id",rid);
			params1.put("M_id",tn);
			rightDao.insert("saveRightinfo", params1);
		}
		params1.put("uuid",WebAppHelper.newEventId());
		params1.put("role_id",roleid);
		params1.put("right_id",rid);
		rightDao.insert("saveRoleRight", params1);
		return null;
	}
	
	public List<Map<String,Object>> saveCheckNode(String checkNode,String uuid){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("uuid",WebAppHelper.newEventId());
		params.put("uid",uuid);
		params.put("rid",checkNode);
		rightDao.delete("deleteUserRoleById", params);
		rightDao.insert("saveUserRole", params);
		return null;
	}
}
