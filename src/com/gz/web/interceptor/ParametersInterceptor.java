package com.gz.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 
 * @author Administrator
 *
 */
public class ParametersInterceptor extends HandlerInterceptorAdapter {
		  
	    @Override  
	   public void afterCompletion(HttpServletRequest request,  
	            HttpServletResponse response, Object obj, Exception err)  
	           throws Exception {  
	    }  
	  
	    /**
	     * 
	     * postHandle在业务处理器处理请求执行完成后，生成视图之前执行。
	     * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染），
	     * 有机会修改ModelAndView
	     */
	    
	    @Override  
	    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
	            Object obj, ModelAndView mav) throws Exception {  
	        
	    }  
	  
	    /**
	     * preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制等处理；
	     */
	    @Override  
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
	            Object obj) throws Exception {  
	        String str = (String) request.getSession().getAttribute("isLogin");  
	        if(str!=null){  
	            return true;  
	        }  
	        return false;  
	    }  
	
}
