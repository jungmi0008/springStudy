package com.member.dao;

import com.member.dto.MemberDTO;

public interface MemberDAO {
	
	//회원등록
	void insertMember(MemberDTO dto) throws Exception;

	//로그인 체크
	public String loginCheck(MemberDTO dto) throws Exception;
}
