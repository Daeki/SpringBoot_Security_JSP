package com.iu.s1.member;

import java.util.List;

public class MemberVO_Back {
	
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
	
	

	
//	private MemberFileDTO memberFileDTO;
//	
//	
//	
//	public MemberFileDTO getMemberFileDTO() {
//		return memberFileDTO;
//	}
//	public vousername setMemberFileDTO(MemberFileDTO memberFileDTO) {
//		this.memberFileDTO = memberFileDTO;
//	}

	
	
	

}
