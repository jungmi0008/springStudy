package com.member.service;

import javax.servlet.http.HttpSession;

import com.member.dto.MemberDTO;

public interface MemberService {

	//회원 가입
	void insertMember(MemberDTO dto) throws Exception;
	
	//로그인 검사
	public String loginCheck(MemberDTO dto, HttpSession session) throws Exception;
	
	//로그아웃
	public void logout(HttpSession session) throws Exception;
	
	
}
