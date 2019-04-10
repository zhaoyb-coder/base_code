package com.gz.helper;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: WebAppHelper
 * @Description: 
 * <p>Copyright: Copyright (c) 2018 </p>
 * <p>Company: GZ</p>
 * @author ZYB 
 * @date 2018-07-19  
 * @version V1.0
 */
public class WebAppHelper {
	/**
	 * @return
	 */
	public static String newEventId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public static Map<String, String> getUrl(){
//		return (Map<String, String>) DoeConfigHelper.getInstance().getConfigList().get(0);
//	}
	
	/**
	 * @param request
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getDownLoadFileName(HttpServletRequest request, String fileName) throws Exception {
		String userAgent = request.getHeader("user-agent").toLowerCase();  
        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {  //   
        	fileName = URLEncoder.encode(fileName, "UTF-8");  
        } else {  // 
        	fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");  
        }
        return fileName;
	}
	
	@SuppressWarnings("unchecked")
	public static String getLoginUserid(HttpServletRequest request){
		//获取sessionid
		String sessid = request.getSession().getId();
		
		//获取application中存的用户list
		ServletContext application = request.getServletContext();
		Map<String,String> loginMap = new HashMap<String,String>();
		loginMap = (Map<String, String>) application.getAttribute("loginLimit");
		String userid = loginMap.get(sessid);
		
		return userid;
	}
}
