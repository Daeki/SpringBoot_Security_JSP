package com.iu.s1.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	
	//role
	public int setRole(RoleVO roleVO)throws Exception;
	
	
	public MemberVO getIdCheck(MemberVO memberVO)throws Exception;
	
	
	public int getUpdate(MemberVO memberVO)throws Exception;
	
	public int getDelete(MemberVO memberVO)throws Exception;
	
	public int setMemberRole(Map<String, String> map) throws Exception;
	
	//memberJoin 데이터를 받아서 DB에 insert 하는 메서드
	public int setInsert(MemberVO memberVO)throws Exception;
	//setFileInsert
	public int setFileInsert(MemberFileVO memberFileVO)throws Exception;
	
	
	//login - id pw를 받아서 조회
	public MemberVO getLogin(String username);
	
//	public MemberFileVO memberLoginFile(MemberVO memberVO)throws Exception;
//	
//	public MemberFileVO getMemberFile(MemberVO memberVO)throws Exception;


}
