package com.iu.s1.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberVO implements UserDetails{
	
	//모든 멤버변수는 private
	//변수명과 데이타타입은 컬럼과 동일하게
	//getter, setter
	//기본생성자 1개
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private Boolean enabled;
	
	
	private List<RoleVO> roleVOs;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList();
//      authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getName()));
//      authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getName()));
      for(RoleVO roleVO:this.getRoleVOs()) {
      	System.out.println(roleVO.getRoleName());
      	authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
      }
		return authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public List<RoleVO> getRoleVOs() {
		return roleVOs;
	}


	public void setRoleVOs(List<RoleVO> roleVOs) {
		this.roleVOs = roleVOs;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	

}
