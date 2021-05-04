package com.iu.s1.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;


/**
 * 
 * 로그인 성공 후 권한 에러가 발생 했을 때 실행하는 핸들러
 * 
 * 로그인 안하고 권한 에러는 발생 하지 않는 듯
 * 
 * 
 * */

public class SecurityException implements AccessDeniedHandler{
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			System.out.println("User '" + authentication.getName() +
                "' attempted to access the URL: " +
                request.getRequestURI());
		}
		
//		foward		------------------------------------------------------
//		request.getRequestDispatcher("").forward(request, response);
		
//		redirect 	------------------------------------------------------
		response.sendRedirect("/accessError");
		
	}

}
