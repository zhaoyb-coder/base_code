package com.gz.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) arg0;
		HttpServletResponse httpResponse = (HttpServletResponse) arg1;
		String loginUrl = httpRequest.getContextPath();
		
		String url = httpRequest.getRequestURI();
		if(url.equals("/mvn_demo/") || url.equals("/mvn_demo/pages/sys/login.jsp")){
			
		}else{
		if (httpRequest.getSession(false) == null) {
			String str = "<script language='javascript'>alert('由于长时间未操作,请重新登录');"
					+ "window.top.location.href='"
					+ loginUrl
					+ "';</script>";
			httpResponse.setContentType("text/html;charset=UTF-8");// 解决中文乱码
			PrintWriter writer = httpResponse.getWriter();
			writer.write(str);
			writer.flush();
			return;
		}else if(httpRequest.getSession(false) != null){
			//判断 sessionid 对应的用户 是否存在
			ServletContext application = httpRequest.getServletContext();
			String sessid = httpRequest.getSession().getId();
			
			Map<String,String> loginMap = new HashMap<String,String>();
			
			loginMap = (Map<String, String>) application.getAttribute("loginLimit");
			if(loginMap == null || loginMap.size() == 0){
				//没有任何人登录系统
				String str = "<script language='javascript'>alert('请先登录系统');"
						+ "window.top.location.href='"
						+ loginUrl
						+ "';</script>";
				httpResponse.setContentType("text/html;charset=UTF-8");// 解决中文乱码
				PrintWriter writer = httpResponse.getWriter();
				writer.write(str);
				writer.flush();
				return;
			}else{
				if(loginMap.get(sessid) == null){
					String str = "<script language='javascript'>alert('请先登录系统');"
							+ "window.top.location.href='"
							+ loginUrl
							+ "';</script>";
					httpResponse.setContentType("text/html;charset=UTF-8");// 解决中文乱码
					PrintWriter writer = httpResponse.getWriter();
					writer.write(str);
					writer.flush();
					return;
				}
			}
		}
		}
		arg2.doFilter(httpRequest, httpResponse);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

}
