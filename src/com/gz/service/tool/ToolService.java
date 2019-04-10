package com.gz.service.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gz.dao.BaseDao;
import com.gz.dao.tool.ToolDao;
import com.gz.helper.WebAppHelper;
import com.gz.service.BaseService;
import com.gz.vo.tool.ToolFile;

@Service("toolService")
public class ToolService extends BaseService{
	
	@Autowired
	private ToolDao toolDao;
	
	@Override
	public BaseDao getDao() {
		return toolDao;
	}
	
	public Map<String,Object> saveImgFile(MultipartFile uploadImgFile) throws Exception{
		ToolFile file = new ToolFile();
		
		String fileName = uploadImgFile.getOriginalFilename();
		String type = uploadImgFile.getContentType().split("/")[0]; 
	    //String path = HttpServletRequest.getRequest().getServletContext().getRealPath("/upload/excel");
	    String path ="E:/toolFiles/imgFile";
		//获取指定文件或文件夹在工程中真实路径，getRequest()这个方法是返回一个HttpServletRequest，封装这个方法为了处理编码问题
	    FileOutputStream fos = FileUtils.openOutputStream(new File(path+"/" +fileName));//打开FileOutStrean流
	    IOUtils.copy(uploadImgFile.getInputStream(),fos);//将MultipartFile file转成二进制流并输入到FileOutStrean
	    fos.close();
	    
	    file.setFileid(WebAppHelper.newEventId());
	    file.setDownpath(path+"/" +fileName);
	    file.setFilename(fileName);
	    file.setFiletype(type);
	    file.setUpload_date(new Date());
	    toolDao.insert("add_modal", file);
	    
	    Map<String ,Object> map = new HashMap<String,Object>();
	    map.put("msg", "success");
	    return map;
	}
}
