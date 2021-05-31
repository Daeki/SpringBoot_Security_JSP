package com.iu.s1.member;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberMapper memberMapper;
	
	
	@RequestMapping("memberPage")
	public void memberPage(HttpSession session)throws Exception{
		System.out.println("---------------- Member Page -----------------");
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			System.out.println(en.nextElement());
		}
		
		Object obj=(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		
		SecurityContextImpl sc = (SecurityContextImpl)obj;
		
		Authentication authentication = sc.getAuthentication();
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		System.out.println(memberVO.getEmail());
		//로그인 시 Session 의 속성 값
		System.out.println("---------------- Member Page -----------------");
	}
	
	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session, Authentication authentication){
		//로그인 시 Session 의 속성 명
		Object obj=(session.getAttribute("SPRING_SECURITY_CONTEXT"));
		
		//로그인 시 Session 의 속성 값
		SecurityContextImpl sc = (SecurityContextImpl)obj;
		
		//로그인 한 유저의 정보가 있는 곳
		Authentication authentication2 = sc.getAuthentication();
		System.out.println(authentication2.getName());
		
		System.out.println("====================================================");
		System.out.println(obj.getClass().getName());
		System.out.println(authentication);
		
		System.out.println(authentication.getName());
		System.out.println(authentication.getDetails());
		System.out.println("Login Success");
		return "redirect:../";
	}
	
//	@PostMapping
//	public String memberLogin(MemberVO memberVO)throws Exception{
//		UserDetails user = memberService.loadUserByUsername(memberVO.getUsername());
//		
//		return "redirect:../";
//	}
	
	
	 @PostMapping("/member/memberLogin") public String
	  memberLogin(HttpServletRequest request, Model model)throws Exception{
	  System.out.println("Login Fail -----------------------------");
	  Enumeration<String> em = request.getParameterNames();
	  while(em.hasMoreElements()) { System.out.println(em.nextElement()); }
	  
	  System.out.println(request.getAttribute("LoginFailMessage"));
	  System.out.println("Login Fail -----------------------------");
	  
	  model.addAttribute("msg", "ttttttttttttttttttttttttttttttt");
	  model.addAttribute("path", "./memberLogin?error"); return
	  "member/memberLogin"; }
	 
	
	@GetMapping("memberLogin")
	public void memberLogin(@RequestParam(value="error", required=false) String error, Model model, HttpSession session
			)throws Exception{
        if(error != null) {
        	System.out.println("에러 발생 !!!!!!!");
        	System.out.println(error);
            
            
            Enumeration<String> names = session.getAttributeNames();
            while(names.hasMoreElements()) {
            	System.out.println(names.nextElement());
            }
            Object obj = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            System.out.println(obj);
            System.out.println("============ Fail Session =============");
            String loginFailMessage = "";
    		if( obj instanceof BadCredentialsException) {
    			loginFailMessage = "비번이 틀림";
    		}else if( obj instanceof InternalAuthenticationServiceException) {
    			loginFailMessage = "아이디가 없음";
    		}else if( obj instanceof AuthenticationCredentialsNotFoundException) {
    			loginFailMessage = "인증이 안됌";
    		}else if( obj instanceof LockedException) {
    			loginFailMessage = "계정 잠김";
    		}else if( obj instanceof DisabledException) {
    			loginFailMessage = "휴면 계정";
    		}else if( obj instanceof AccountExpiredException) {
    			loginFailMessage = "계정 유효기간 만료";
    		}else if( obj instanceof CredentialsExpiredException) {
    			loginFailMessage = "비밀번호 유효기간 만료";
    		}else {
    			loginFailMessage = "다시 시도";
    		}
    		model.addAttribute("LoginFailMessage", loginFailMessage);
            
        }
	}
	
	@GetMapping("memberJoin")
	public void memberJoin()throws Exception{}
	
	@PostMapping("memberJoin")
	public String memberJoin(MemberVO memberVO, MultipartFile avatar,HttpSession session, Model model)throws Exception{

		System.out.println("Controller Start !!!!!!");
		
		int result = memberService.memberJoin(memberVO, avatar, session);
//		System.out.println(avatar.getName());//파라미터명
//		System.out.println(avatar.getOriginalFilename());//upload 할 때 파일명
//		System.out.println(avatar.getSize());//파일의 크기(byte)
//		System.out.println(avatar.isEmpty());//파일의 존재 유무
		
		System.out.println("Controller Finish !!!!!!");
		String message = "회원가입 실패";
		String path="./memberJoin";
//		int result=1;
		if(result>0) {
			message ="회원 가입 성공";
			path="../";
		}
		
		model.addAttribute("msg", message);
		model.addAttribute("path", path);
		return "common/commonResult";
	}
	
//	@GetMapping("memberIdCheck")
//	public String memberIdCheck(MemberVO memberDTO, Model model)throws Exception{
//		memberDTO = memberService.memberIdCheck(memberDTO);
//		String result = "0";//0 사용 불가 1:사용가능
//		if(memberDTO==null) {
//			result="1";
//		}
//		
//		model.addAttribute("result", result);
//		
//		return "common/ajaxResult";
//	}
//	
//	@RequestMapping("memberUpdate")
//	public void memberUpdate()throws Exception{}
//	
//	@RequestMapping(value="memberUpdate", method = RequestMethod.POST)
//	public String memberUpdate(MemberVO memberDTO, HttpSession session)throws Exception{
//		int result = memberService.memberUpdate(memberDTO);
//		
//		if(result>0) {
//			session.setAttribute("member", memberDTO);
//		}
//		return "redirect:../";
//	}
//	
//	@RequestMapping("memberDelete")
//	public String memberDelete(HttpSession session)throws Exception{
//		MemberVO memberDTO =(MemberVO)session.getAttribute("member");
//		int result = memberService.memberDelete(memberDTO, session);
//		
//		session.invalidate();
//		
//		return "redirect:../";
//	}
//	
//	@RequestMapping("memberPage")
//	public void memberPage()throws Exception{
//		
//	}
//	
//	@RequestMapping("memberLogout")
//	public String memberLogout(HttpSession session)throws Exception{
//		session.invalidate();
//		return "redirect:../";
//	}
//	
//	
//	@RequestMapping("memberLogin")
//	public void memberLogin()throws Exception{}
//	
//	@RequestMapping(value="memberLogin", method = RequestMethod.POST)
//	public String memberLogin(MemberVO memberDTO, HttpSession session)throws Exception{
//		memberDTO = memberService.memberLogin(memberDTO);
//		System.out.println(memberDTO);
//		
//		session.setAttribute("member", memberDTO);
//		
//		
//		return "redirect:../";
//	}
//	
//	@RequestMapping("memberJoinCheck")
//	public void memberJoinCheck()throws Exception{}
	


}
