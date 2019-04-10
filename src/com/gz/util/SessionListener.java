package com.gz.util;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener{
	private static Logger log = Logger.getLogger(SessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		//HttpSession session = event.getSession();
		//String sessionId = session.getId();
		//在session销毁的时候,把loginUserMap中保存的键值对清除
		//@SuppressWarnings("unchecked")
		//String uuid = session.getAttribute("now_user").toString();
		/*if (uuid != null) {
		@SuppressWarnings("unchecked")
		Map<String, String> loginUserMap = (Map<String, String>) event.getSession().getAttribute("loginUserMap");
		if(loginUserMap.get(uuid).equals(sessionId)){
				log.info("----------------clean user from application : " + uuid);
				loginUserMap.remove(uuid);
				event.getSession().setAttribute("loginUserMap", loginUserMap);
			}
		}*/
	}

}
