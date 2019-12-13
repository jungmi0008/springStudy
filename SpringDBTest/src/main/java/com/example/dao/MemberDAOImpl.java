package com.example.dao;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.dto.MemberVO;

@Repository	//MemberDAOImpl 객체 생성
public class MemberDAOImpl implements MemberDAO {

	@Inject	//sqlSession 객체 생성.
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.example.mapper.memberMapper";
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		return sqlSession.selectList(Namespace + ".selectMember");
		//selectList는 sqlSession 내장된 함수
		//memberMapper.xml에 selectMember를 아이디로 하는 태그를 찾는다.
		//해당 태그가 가지고 있는 쿼리문을 실행한다.
		 /**
		   * Retrieve a list of mapped objects from the statement key and parameter.
		   * @param <E> the returned list element type
		   * @param statement Unique identifier matching the statement to use.
		   * @return List of mapped object
		   * <E> List<E> selectList(String statement);
		   */
		  
	}
	
}

















