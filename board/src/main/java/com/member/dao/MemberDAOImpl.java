package com.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.member.dto.MemberDTO;

@Repository //Spring에서 관리하는 dao bean으로 설정
public class MemberDAOImpl implements MemberDAO {
	
	@Inject //mybatis 실행을 위한 SqlSession 객체를 주입시킨다.
	SqlSession sqlSession;
	
	//-----------------------------------------------------------
	// namespace 는 Mapper의 namespace와 이름이 같아야 한다.
	//-----------------------------------------------------------
	private static String namespace = "com.edu.mappers.member";

	@Override
	public void insertMember(MemberDTO dto) throws Exception {
		sqlSession.insert(namespace + ".insertMember", dto);
	}

	//로그인 검사
	@Override
	public String loginCheck(MemberDTO dto) throws Exception {
		return sqlSession.selectOne(namespace + ".login_check", dto);
	}

}
















