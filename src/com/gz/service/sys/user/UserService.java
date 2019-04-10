package com.gz.service.sys.user;


import java.util.Date;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gz.dao.BaseDao;
import com.gz.dao.FileInfoDao;
import com.gz.dao.sys.user.UserDao;
import com.gz.helper.FieldMappingHelper;
import com.gz.helper.WebAppHelper;
import com.gz.helper.mapping.YmdDateMapping;
import com.gz.service.BaseService;
import com.gz.util.FileUtil;
import com.gz.util.MD5;
import com.gz.vo.FileInfo;
import com.gz.vo.login.User;

@Service("userService")
public class UserService extends BaseService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileInfoDao fileInfoDao;
	
	
	@Override
	public BaseDao getDao() {
		return userDao;
	}
	
	/**
	 * 查询列表
	 * @param pageSize
	 * @param curPage
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> user_list(Map<String,String> params,int from,int pageSize) throws Exception{
		return queryForPage("user_list", params, from, pageSize);
	}
	
	
	public String add(MultipartFile uploadFile,User user,String uploadFilePath) throws Exception{
		String userid = WebAppHelper.newEventId();
		
		if(uploadFile != null){
			//上传文件
			Map<String,Object> filemap = FileUtil.saveFile(uploadFile, "userFile");
			
			//保存文件信息
			FileInfo fileinfo = new FileInfo();
			fileinfo.setId(WebAppHelper.newEventId());
			fileinfo.setFileid(filemap.get("id")+"");
			fileinfo.setCreate_time(new Date());
			fileinfo.setDownpath(filemap.get("path")+"");
			fileinfo.setRealname(filemap.get("name")+"");
			fileinfo.setUpdate_time(new Date());
			fileinfo.setUpload_user(userid);
			fileinfo.setUploadpath(uploadFilePath);
			
			//用户、文件关联信息
			user.setFile(filemap.get("id")+"");
			
			fileInfoDao.insert("addModal", fileinfo);
		}
		
		//完善user信息
		user.setId(userid);
		user.setPassword(MD5.string2MD5(user.getPassword()));
		user.setDel_flag("1");
		user.setCreate_time(new Date());
		user.setUpdate_time(new Date());
		user.setStatus("1");
		
		//保存信息
		userDao.insert("addModal", user);
		
		return "success";
	}
	
	public String update(MultipartFile uploadFile,User user,String uploadFilePath) throws Exception{
		if(uploadFile != null){
			//上传文件
			Map<String,Object> filemap = FileUtil.saveFile(uploadFile, "userFile");
			
			//保存文件信息
			FileInfo fileinfo = new FileInfo();
			fileinfo.setId(WebAppHelper.newEventId());
			fileinfo.setFileid(filemap.get("id")+"");
			fileinfo.setCreate_time(new Date());
			fileinfo.setDownpath(filemap.get("path")+"");
			fileinfo.setRealname(filemap.get("name")+"");
			fileinfo.setUpdate_time(new Date());
			fileinfo.setUpload_user(user.getId());
			fileinfo.setUploadpath(uploadFilePath);
			
			//用户、文件关联信息
			user.setFile(filemap.get("id")+"");
			
			fileInfoDao.insert("addModal", fileinfo);
		}
		
		//完善user信息
		user.setPassword(MD5.string2MD5(user.getPassword()));
		user.setUpdate_time(new Date());
		
		//保存信息
		userDao.update("updateModal", user);
		
		return "success";
	}
	
	public String delete_user(String[] userids){
		
		for(String userid : userids){
			userDao.delete("deleteModal", userid);
		}
		
		//Map<String,Object> map = userDao.queryOneMap("queryById", userid);
		
		//存在附件、删除表数据 和 服务器文件
		/*if(!"".equals(map.get("file")+"")){
			deleteFile(map.get("file"));
		}*/
		
		return "success";
	}
	
	/**
	 * 通过userid 获取user详细信息
	 * @param userid
	 * @return
	 */
	public JSONObject queryById(String userid){
		Map<String,Object> map = userDao.queryOneMap("queryById", userid);
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat));
		return jsonObject;
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
			FieldMappingHelper.process(list, "create_time", new YmdDateMapping()); //时间映射
		}
		return listPageData2DataGrid(new PageInfo<Map<String,Object>>(list).getTotal(), list);  //返回前端easyui-datagrid需要的数据格式
	}
}
