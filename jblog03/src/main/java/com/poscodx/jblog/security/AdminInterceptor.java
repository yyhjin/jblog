package com.poscodx.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.poscodx.jblog.vo.UserVo;


public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// PathVariable을 Map에 매핑해줌
		Map<String, String> pathVariables = (Map<String, String>) request
                    .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		
		String id = pathVariables.get("id");
		
		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null) {
			request
			.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
			.forward(request, response);

			return false;
		}
		
		if(!id.equals(authUser.getName())) {
			response.sendRedirect(request.getContextPath()+"/"+id);

			return false;
		}
		
		return true;
	}
	
}
