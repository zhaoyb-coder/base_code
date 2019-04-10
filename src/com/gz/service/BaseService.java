package com.gz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.activiti.engine.repository.Deployment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gz.dao.BaseDao;
import com.gz.helper.DateHelper;
import com.gz.helper.FieldMappingHelper;
import com.gz.helper.mapping.YmdDateMapping;


/**
 * @ClassName: BaseServiceImpl
 * @Description:  
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB 
 * @date 2018-07-13  
 * @version V1.0
 */
public abstract class BaseService extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public abstract BaseDao getDao();

	
	/**
	 *  
	 * @param method  
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> queryListMap(String method, Object param) {
		return getDao().queryListMap(method, param);
	}
	/**
	 *  
	 */
	public Map<String, Object> queryById(String method, Object param) {
		return getDao().queryOneMap(method, param);
	}
	
	/**
	 *  
	 * @param total
	 * @param list
	 * @return
	 */
	public Map<String, Object> listPageData2DataGrid(long total, List<?> list) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 
		jsonMap.put("total", total);//total 
        jsonMap.put("rows", list);//rows 
        return jsonMap;
	}
	
	/**
	 * 流程实例 转为 前台easyui可用的数据格式
	 * @param total
	 * @param list
	 * @return
	 */
	public Map<String, Object> listDeployment2DataGrid(long total, List<Deployment> list) {
		List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		for(Deployment dep : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", dep.getId());
			map.put("category", dep.getCategory());
			map.put("deploymentTime", DateHelper.date2String(dep.getDeploymentTime(), "yyyy-MM-dd"));
			map.put("name", dep.getName());
			map.put("tenantId", dep.getTenantId());
			li.add(map);
		}
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 
		jsonMap.put("total", total);//total 
        jsonMap.put("rows", li);//rows 
        return jsonMap;
	}
	/**
	 * 
	 * @param total
	 * @param list
	 * @param footerlist
	 * @return
	 */
	public Map<String, Object> listPageData2DataGrid(long total, List<?> list, List<?> footerlist) {
		 Map<String, Object> jsonMap = new HashMap<String, Object>();// 
		jsonMap.put("total", total);//total 
        jsonMap.put("rows", list);//rows 
        jsonMap.put("footer", footerlist);//rows 
        return jsonMap;
	}
	
	/**
	 * 
	 * @param total
	 * @param list
	 * @return
	 */
	public Map<String, Object> listPage2DataGridWithMsg(long total, List<?> list, Object message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 
		jsonMap.put("total", total);//total 
		jsonMap.put("rows", list);//rows 
		jsonMap.put("msg", message);// 
		return jsonMap;
	}
	
	/**
	 * @param total 
	 * @param list
	 * @param footerName  
	 * @param footerField  
	 * @param footerValueField  
	 * @param valueFooter    
	 * @return
	 */
	public Map<String, Object> listPageData2DataGrid(long total, List<Map<String, Object>> list,String footerField,String footerValueField,String footerName,String valueFooter) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 
		Map<String, Object> map = new HashMap<String, Object>(); 
		List<Map<String, Object>> listResults2=new ArrayList<Map<String,Object>>();
		jsonMap.put("total", total);//total 
        jsonMap.put("rows", list);//rows 
        	if(!list.isEmpty()){
        		map.put(footerField,footerName);
        		map.put(footerValueField,list.get(0).get(valueFooter));
        	}
        	listResults2.add(map);
        jsonMap.put("footer", listResults2);
        return jsonMap;
	}
	/**
	 * 后台查询数据 转换为 前台需要的数据类型
	 * 使用 PageHelper分页工具类
	 * @param method 
	 * @param params
	 * @param from
	 * @param PageSize
	 * @return
	 */
	public Map<String,Object> listData2PageData(String method,Map<String,Object> params,int curPage,int PageSize) throws Exception{
		PageHelper.startPage(curPage, PageSize); //开始分页
		List<Map<String,Object>> list = queryListMap(method, params); //查询数据
	
		long total = new PageInfo<Map<String,Object>>(list).getTotal();  //返回前端需要的数据格式
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		jsonMap.put("recordsTotal", total);//recordsTotal键 存放总记录数，必须的  
        jsonMap.put("data", list);//rows键 存放每页记录 list
        jsonMap.put("recordsFiltered", list.size());//recordsFiltered键 搜索后所有的数据条数
        
		return jsonMap;
	}

}
