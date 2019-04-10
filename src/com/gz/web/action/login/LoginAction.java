package com.gz.web.action.login;


import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.gz.service.login.LoginService;
import com.gz.util.CodeUtil;
import com.gz.vo.login.User;
import com.gz.web.action.BaseAction;

/**
 * 登录请求接口
 * @author zhaoyubo
 *
 */
@Controller
@RequestMapping("/gz/login/loginAction")
public class LoginAction extends BaseAction{
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value={"/login"})
	@ResponseBody
	public String login(User user,HttpServletRequest request, HttpServletResponse response,String verity) {
		try{
			//判断验证码是否正确
			/*if(!ValidateCode(verity,request)){
				return "验证码错误";
			}*/
			
			//判断用户名、密码是否正确
			String result = loginService.login(user);
			if("success".equals(result)){
				request.getSession().setAttribute("isLogin", result);
				loginLimit(request,user);
			}
			
			return result;
		}catch(Exception e){
			log.error("登录失败",e);
		}
		return "用户名或密码错误";
	}
	@RequestMapping(value={"/getCode"})
	@ResponseBody
	public String getCode(HttpServletRequest req, HttpServletResponse resp) {
		try{
			// 调用工具类生成的验证码和验证码图片
	        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

	        // 将四位数字的验证码保存到Session中。
	        HttpSession session = req.getSession();
	        session.setAttribute("code", codeMap.get("code").toString());

	        // 禁止图像缓存。
	        resp.setHeader("Pragma", "no-cache");
	        resp.setHeader("Cache-Control", "no-cache");
	        resp.setDateHeader("Expires", -1);

	        resp.setContentType("image/jpeg");

	        // 将图像输出到Servlet输出流中。
	        ServletOutputStream sos;
	        try {
	            sos = resp.getOutputStream();
	            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
	            sos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
		}catch(Exception e){
			log.error("获取验证码",e);
		}
		return null;
	}
	
	@RequestMapping(value={"/logout"})
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try{
			String sessionid = request.getSession().getId();
			ServletContext application = request.getServletContext();
			
			//退出的时候 去除application中此sessionid关联的user信息
			@SuppressWarnings("unchecked")
			Map<String, String> loginMap = (Map<String, String>) application.getAttribute("loginLimit");
			if(loginMap != null && loginMap.size() >= 0){
				
				loginMap.remove(sessionid);
				
				//保存用户登录id到application
				application.setAttribute("loginLimit", loginMap);
				
			}
			
			return "success";
		}catch(Exception e){
			log.error("退出登录",e);
		}
		return "success";
	}
	@RequestMapping(value={"/query_menu"})
	@ResponseBody
	public JSONArray query_menu(HttpServletRequest request) {
		try{
			return loginService.query_menu(request);
		}catch(Exception e){
			log.error("登录失败",e);
		}
		return null;
	}	
	@RequestMapping(value={"/query_topMenu"})
	@ResponseBody
	public JSONArray query_topMenu(HttpServletRequest request) {
		try{
			return loginService.query_topMenu(request);
		}catch(Exception e){
			log.error("登录失败",e);
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public void loginLimit(HttpServletRequest request,User u){
		//登录的所有人 包括在一个list里面，存在application中
		ServletContext application = request.getServletContext();
		String session_id = request.getSession().getId();
		
		//保存用户信息  sessionid : loginname
		Map<String,String> loginMap = new HashMap<String,String>();
		
		loginMap = (Map<String, String>) application.getAttribute("loginLimit");
		
		if(loginMap == null || loginMap.size() == 0){
			//用户登录人数为0
			loginMap = new HashMap<String,String>();
			
			loginMap.put(session_id, u.getLogin_name());
			
			//保存用户登录id到application
			application.setAttribute("loginLimit", loginMap);
			
		}else{
			//已有登录用户，添加..
			loginMap.put(session_id, u.getLogin_name());
			application.setAttribute("loginLimit", loginMap);
		}
	}
	
	public boolean ValidateCode(String code,HttpServletRequest request){
		String session_code = request.getSession().getAttribute("code")+"";
		
		//转换成小写
		session_code = session_code.toLowerCase();
		code = code.toLowerCase();
		
		if(session_code.equals(code)){
			return true;
		}
		return false;
	}
}
