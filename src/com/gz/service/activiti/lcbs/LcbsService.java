package com.gz.service.activiti.lcbs;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.gz.dao.BaseDao;
import com.gz.service.BaseService;
import com.gz.util.FileUtil;

import com.github.pagehelper.PageInfo;

@Service("lcbsService")
public class LcbsService extends BaseService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Autowired
	private FileInfoDao fileInfoDao;*/
	//注入activitiService服务
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Override
	public BaseDao getDao() {
		return null;
	}
	
	/**
	 * 查询列表
	 * @param pageSize
	 * @param curPage
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> lc_list(Map<String,String> params,int from,int pageSize) throws Exception{
		
		return queryForPage("",params,from,pageSize);
	}
	
	/**
	 * zip 部署流程
	 * @param response
	 * @param uploadFile
	 * @return
	 * @throws IOException 
	 */
	public String createDeployment(HttpServletResponse response,MultipartFile uploadFile) throws IOException{
		repositoryService.createDeployment() //创建部署
		.name(FileUtil.getNameNoSuffix(uploadFile.getOriginalFilename()))	//需要部署流程名称
		.addZipInputStream(new ZipInputStream(uploadFile.getInputStream()))//添加ZIP输入流
		.deploy();//开始部署
		
		return "success";
	}
	
	/**
	 * 整合返回前台的数据格式
	 * @param method
	 * @param params
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> queryForPage(String method, Map<String, String> params, int from, int pageSize){
		PageHelper.startPage(from, pageSize); //开始分页
		/*//取得总数量
		long deployCount=repositoryService.createDeploymentQuery().deploymentNameLike("%"+"level2qingjia"+"%")
				.count();*/
		List<Deployment> list= repositoryService.createDeploymentQuery()//创建流程查询实例
				.orderByDeploymenTime().desc()  //降序
				//.deploymentNameLike("%"+"level"+"%")   //根据Name模糊查询
				.listPage(from-1, pageSize);
		
		return listDeployment2DataGrid(new PageInfo<Deployment>(list).getTotal(), list);  //返回前端easyui-datagrid需要的数据格式
	}
}
