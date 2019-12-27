package com.member.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.member.dao.MemberDAO;
import com.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	//서비스는 dao를 호출한다. => @Inject를 사용한다.
	@Inject
	MemberDAO memberDao;
	
	//회원정보 등록
	@Override
	public void insertMember(MemberDTO dto) throws Exception {
		memberDao.insertMember(dto);
	}

	//로그인 검사
	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) throws Exception {
		// 로그린 성공 => 이름이 넘어온다. 실패 =>null이 넘어온다.
		String name = memberDao.loginCheck(dto);
		
		if(name != null) { //아이디와 비밀번호가 맞은 경우
			//세션변수 등록
			session.setAttribute("userid", dto.getId());
			session.setAttribute("name",   name);
		}
		return name;
	}

	//로그 아웃
	@Override
	public void logout(HttpSession session) throws Exception {
		//세션을 초기화 한다.
		session.invalidate();
	}

}



















