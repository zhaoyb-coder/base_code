package com.gz.service.activiti.qingjia;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.gz.dao.BaseDao;
import com.gz.dao.activiti.qingjia.QjDao;
import com.gz.helper.FieldMappingHelper;
import com.gz.helper.WebAppHelper;
import com.gz.helper.mapping.DomainFieldMapping;
import com.gz.helper.mapping.YmdDateMapping;
import com.gz.service.BaseService;
import com.gz.service.common.SysDomainService;
import com.gz.vo.activiti.qingjia.Qingjia;

import com.github.pagehelper.PageInfo;

@Service("qjService")
public class QjService extends BaseService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Autowired
	private FileInfoDao fileInfoDao;*/
	//注入activitiService服务
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Autowired
	private SysDomainService sysDomainService;
	
	
	@Autowired
	private QjDao qjDao;
	
	@Override
	public BaseDao getDao() {
		return qjDao;
	}
	
	/**
	 * 添加
	 * @param qingjia
	 * @return
	 */
	public String add(String userid,Qingjia qingjia){
		//开启流程  
		String leaveId = WebAppHelper.newEventId();
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("leaveId", leaveId);
		
		// 启动流程
		ProcessInstance pi= runtimeService.startProcessInstanceByKey("myProcess",variables); 
		
		// 根据流程实例Id查询任务
		Task task=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
		
		 // 完成 学生填写请假单任务		
		taskService.complete(task.getId());
		
		//插入业务表
		qingjia.setEventid(leaveId);
		qingjia.setTask_state("0");
		qingjia.setProcessInstanceId(pi.getProcessInstanceId());
		qingjia.setUserId(userid);
		qjDao.insert("addModel", qingjia);
		
		return "success";
	}
	
	
	/**
	 * 查询列表
	 * @param pageSize
	 * @param curPage
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> qj_list(Map<String,String> params,int from,int pageSize) throws Exception{
		return queryForPage("qj_list", params, from, pageSize);
	}
	
	
	/**
	 * 分页查询，查询复杂sql语句
	 * @param method
	 * @param params
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> queryForPage(String method, Map<String, String> params, int from, int pageSize){
		PageHelper.startPage(from, pageSize); //开始分页
		List<Map<String, Object>> list = queryListMap(method, params);
		if(list != null && list.size() > 0){
			FieldMappingHelper.process(list, "start_time", new YmdDateMapping()); //时间映射
			FieldMappingHelper.process(list, "task_state", new DomainFieldMapping(sysDomainService.querySysDomain("data_state")));  //任务状态
			FieldMappingHelper.process(list, "task_type", new DomainFieldMapping(sysDomainService.querySysDomain("task_type")));
		}
		return listPageData2DataGrid(new PageInfo<Map<String,Object>>(list).getTotal(), list);  //返回前端easyui-datagrid需要的数据格式
	}
}
