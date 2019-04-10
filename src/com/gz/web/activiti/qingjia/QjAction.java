package com.gz.web.activiti.qingjia;

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

import com.gz.helper.WebAppHelper;
import com.gz.service.activiti.lcbs.LcbsService;
import com.gz.service.activiti.qingjia.QjService;
import com.gz.vo.activiti.qingjia.Qingjia;
import com.gz.vo.login.User;
import com.gz.web.action.BaseAction;

@Controller
@RequestMapping("/gz/sys/activiti/qjAction")
public class QjAction extends BaseAction{
	@Autowired
	private QjService qjService;
	
	/**
	 * 查询流程列表
	 * @param request
	 * @param length
	 * @param start
	 * @param columns
	 * @return
	 */
	@RequestMapping(value={"/qj_list"})
	@ResponseBody
	public Map<String,Object> qj_list(String rows, String page, String strquery) {
		try{
			int curPage = getCurPage(page);
			int pageSize = getPageSize(rows);
			Map<String, String> params = new HashMap<String, String>();
			if(strquery != null)params.put("strquery",decodeStrQuery(strquery));
			Map<String,Object> result = qjService.qj_list(params, curPage, pageSize);
			return result;
		}catch(Exception e){
			log.error("获取请假列表失败",e);
		}
		return null;
	}
	
	@RequestMapping(value={"/add"})
	@ResponseBody
	public String add(HttpServletRequest request,Qingjia qingjia) {
		try{
			return qjService.add(WebAppHelper.getLoginUserid(request),qingjia);
		}catch(Exception e){
			log.error("请假失败",e);
			e.printStackTrace();
		}
		return "error";
	}
}
