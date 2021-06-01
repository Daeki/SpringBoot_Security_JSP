package com.iu.s1.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class KakaoVO implements OAuth2User{
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private Boolean enabled;
	private String social;
	
	private Map<String, Object> attributes;

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		/** 
		* Role을 임시로 입력하였으나 DB에서 조회한 Role로 입력 해도 됨
		*/
		 List<GrantedAuthority> authorities = new ArrayList();
	      authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		return authorities;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
