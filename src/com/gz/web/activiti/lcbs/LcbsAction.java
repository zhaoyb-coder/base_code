package com.gz.web.activiti.lcbs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gz.service.activiti.lcbs.LcbsService;
import com.gz.web.action.BaseAction;

@Controller
@RequestMapping("/gz/sys/activiti/lcbsAction")
public class LcbsAction extends BaseAction{
	@Autowired
	private LcbsService lcbsService;
	
	/**
	 * 查询流程列表
	 * @param request
	 * @param length
	 * @param start
	 * @param columns
	 * @return
	 */
	@RequestMapping(value={"/lc_list"})
	@ResponseBody
	public Map<String,Object> lc_list(String rows, String page, String strquery) {
		try{
			int curPage = getCurPage(page);
			int pageSize = getPageSize(rows);
			Map<String, String> params = new HashMap<String, String>();
			if(strquery != null)params.put("strquery",decodeStrQuery(strquery));
			Map<String,Object> result = lcbsService.lc_list(params, curPage, pageSize);
			return result;
		}catch(Exception e){
			log.error("登录失败",e);
		}
		return null;
	}
	
	/**
	 * 添上传流程部署ZIP文件
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addDeploy")
	@ResponseBody
	public String addDeploy(HttpServletResponse response,@RequestParam(value = "uploadFile",required = false) MultipartFile uploadFile) throws Exception{
		try{
			lcbsService.createDeployment(response,uploadFile); //创建部署
			return "success";
		}catch(Exception e){
			log.error("创建部署失败",e);
		}
		return "error";
	}
	
}
