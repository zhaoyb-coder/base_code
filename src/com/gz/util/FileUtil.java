package com.gz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gz.helper.WebAppHelper;

public class FileUtil {
	
	public static Map<String,Object> saveFile(MultipartFile  uploadImgFile,String path) throws Exception{
		//随即数
		String fileid = WebAppHelper.newEventId();
		
		//获取文件全称
		String fileName = uploadImgFile.getOriginalFilename();
		
		//获取文件后缀
		String suffix = fileName.substring(fileName.indexOf(".")+1);
		//String type = uploadImgFile.getContentType().split("/")[0]; 
	    //String patha = req.getServletContext().getRealPath("/upload/excel");
	    //System.out.println(patha);
		//拼接文件存放目录
		String true_path ="E:/GZ-WorkSpace/File/"+path+"/"+suffix+"/" +fileid+"."+suffix;
		File newFile=new File(true_path);
		
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();//如果目录不存在，创建目录
        }
		
		uploadImgFile.transferTo(newFile);
		
		//获取指定文件或文件夹在工程中真实路径，getRequest()这个方法是返回一个HttpServletRequest，封装这个方法为了处理编码问题
	    /*FileOutputStream fos = FileUtils.openOutputStream(new File(true_path));//打开FileOutStrean流
	    IOUtils.copy(uploadImgFile.getInputStream(),fos);//将MultipartFile file转成二进制流并输入到FileOutStrean
	    fos.close();*/
	    
	    //返回文件信息
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("id",fileid);
	    map.put("path",true_path);
	    map.put("name",fileName);
	    return map;
	}
	
	/**
	 * 获取无后缀文件名
	 * @param uploadImgFile
	 * @return
	 */
	public static String getNameNoSuffix(String fileName){
		System.out.println("----"+fileName+"----");
		
		String[] names = fileName.split("\\.");
		//获取无后缀文件名
		String name = names[0];
		
		return name;
	}
}
