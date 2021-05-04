package com.iu.s1.member;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;


//@Service
public class MemberService_Back  { //implements UserDetailsService
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	

	
	public int memberJoin(MemberVO memberVO, MultipartFile avatar, HttpSession session)throws Exception{
//		String fileName= fileManager.save("upload/member", avatar);	
//		MemberFileVO memberFileVO = new MemberFileVO();
//		memberFileVO.setId(memberVO.getId());
//		memberFileVO.setOrigineName(avatar.getOriginalFilename());
//		memberFileVO.setFileName(fileName);
		memberVO.setPassword(bCryptPasswordEncoder.encode(memberVO.getPassword()));
		
		//memberVO.setEnabled(true);
		int result=0;

		result = memberMapper.setInsert(memberVO);
		
		//Role
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", memberVO.getUsername());
		map.put("role", Role.MEMBER.getName());
		result = memberMapper.setMemberRole(map);
//		result = memberMapper.setFileInsert(memberFileVO);

		
		return result;
	}
	
	//@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println(username);
		MemberVO memberVO = memberMapper.getLogin(username);
		
		System.out.println("Login 시도 !!!!");
        List<GrantedAuthority> authorities = new ArrayList();
//        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getName()));
//        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getName()));
        for(RoleVO roleVO:memberVO.getRoleVOs()) {
        	System.out.println(roleVO.getRoleName());
        	authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
        }
        
        System.out.println(memberVO.getPassword());
        
		return new User(memberVO.getUsername(), memberVO.getPassword(), authorities);
	}

}
