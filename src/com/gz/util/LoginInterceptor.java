package com.gz.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String uid = session.getAttribute("now_user").toString();
		if (session.getAttribute("now_user") == null) {
			response.sendRedirect(request.getContextPath() + "/other/toLogin");
			return false;
		}
		// 多用户登录限制判断,并给出提示信息
		boolean isLogin = false;
		if (uid != null) {
			@SuppressWarnings("unchecked")
			Map<String, String> loginUserMap = (Map<String, String>) session
					.getServletContext().getAttribute("loginUserMap");
			String sessionId = session.getId();
			for (String key : loginUserMap.keySet()) {
				// 用户已在另一处登录
				if (key.equals(uid)
						&& !loginUserMap.containsValue(sessionId)) {
					isLogin = true;
					break;
				}
			}
		}
		if (isLogin) {
			@SuppressWarnings("unchecked")
			Map<String, String> loginOutTime = (Map<String, String>) session
					.getAttribute("loginOutTime");
			session.setAttribute("mess", "用户：" + uid + "，于 "
					+ loginOutTime.get(uid) + " 已在别处登录!");
			loginOutTime.remove(uid);
			session.setAttribute("loginUserMap",
					loginOutTime);
			response.sendRedirect(request.getContextPath() + "/other/toLogin");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
