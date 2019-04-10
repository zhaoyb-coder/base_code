package com.gz.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gz.vo.login.User;

public class LimitLogin {
	private static Logger log = Logger.getLogger(SessionListener.class);
	private static Map<String, String> loginUserMap = new HashMap<String, String>();//存储在线用户
	private static Map<String, String> loginOutTime = new HashMap<String, String>();//存储剔除用户时间
	
	
	public String loginLimite(HttpServletRequest request, String uid) {
		String sessionId = request.getSession().getId();
		for (String key : loginUserMap.keySet()) {
			//用户已在另一处登录
			if (key.equals(uid) && !loginUserMap.containsValue(sessionId)) {
				log.info("用户：" + uid + "，于" + DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "被剔除！");
				loginOutTime.put(uid, DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				loginUserMap.remove(uid);
				break;
		    }
		}
		loginUserMap.put(uid , sessionId);
		request.getSession().setAttribute("loginUserMap", loginUserMap);
		request.getSession().setAttribute("loginOutTime", loginOutTime);
		return "success";
	}
}
