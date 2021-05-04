package com.iu.s1.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginFail implements  AuthenticationFailureHandler {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Login Fail");
		System.out.println("LocalizedMessage : "+exception.getLocalizedMessage());
		System.out.println("Cause : "+exception.getCause());
		System.out.println("Class : "+exception.getClass().toString().substring(exception.getClass().toString().lastIndexOf(".")+1));
		System.out.println("Message : "+exception.getMessage());
		String errorClass = exception.getClass().toString().substring(exception.getClass().toString().lastIndexOf(".")+1);
		String message="";
		switch (errorClass) {
		case "BadCredentialsException":
				message="비번이 틀림";
			break;
		case "InternalAuthenticationServiceException":
			message="아이디가 없음";
		break;
		case "AuthenticationCredentialNotFoundException":
			message="인증이 안됌";
		break;	
		case "LockedException":
			message="계정 잠김";
		break;			
		case "DisabledException":
			message="휴면 계정";
		break;	
		case "AccountExpiredException":
			message="계정 유효기간 만료";
		break;
		case "CredentialExpiredException":
			message="비밀번호 유효기간 만료";
		break;
		default:
			message="다시 시도";
			break;
		}
		System.out.println(message);
//		response.sendRedirect("member/memberLogin?error=true");

		request.setAttribute("LoginFailMessage", message);
		request.getRequestDispatcher("/member/memberLogin?error="+message).forward(request, response);
//		response.sendRedirect("./memberLogin?error="+message);
		
//	       response.setStatus(HttpStatus.UNAUTHORIZED.value());
//	        Map<String, Object> data = new HashMap();
//	        data.put(
//	          "timestamp", 
//	          Calendar.getInstance().getTime());
//	        data.put(
//	          "exception", 
//	          exception.getMessage());
//
//	        response.getOutputStream()
//	          .println(objectMapper.writeValueAsString(data));
//	    }
		/**
		 *  spring security의 예외 세션
		 * AuthenticationFailureHandler
			
			 BadCredentialException
			
			 비밀번호가 일치하지 않을 때 던지는 예외
			
			 InternalAuthenticationServiceException
			
			 존재하지 않는 아이디일 때 던지는 예외
			
			 AuthenticationCredentialNotFoundException
			
			 인증 요구가 거부됐을 때 던지는 예외
			
			 LockedException
			
			 인증 거부 - 잠긴 계정
			
			 DisabledException
			
			 인증 거부 - 계정 비활성화
			
			 AccountExpiredException
			
			 인증 거부 - 계정 유효기간 만료
			
			 CredentialExpiredException
			
			 인증 거부 - 비밀번호 유효기간 만료
			
			
			
			출처: https://to-dy.tistory.com/92 [todyDev]
		 * */
	
	}
}
