package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.board.mappers.board";
	
	//게시물 목록
	@Override
	public List<BoardVO> list() throws Exception {
		return sql.selectList(namespace + ".list");
	}

	//게시물 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		sql.insert(namespace + ".write", vo);
	}

	//게시물 조회
	@Override
	public BoardVO view(int bno) throws Exception {
		return sql.selectOne(namespace + ".view", bno);
	}

	//게시물 수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
	}

	//게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sql.delete(namespace + ".delete", bno);
	}

	//게시물의 총 건수
	@Override
	public int count() throws Exception {
		return sql.selectOne(namespace + ".count");
	}

	//게시물 목록 (페이징)
	//DAO와 매퍼에서는 데이터를 하나만 전송할 수 있기 때문에,
	//2개 이상의 데이터를 사용할 때는 VO(Value Object)나 HashMap을 사용한다.
	@Override
	public List<BoardVO> listPage(int displayPost, int postNum) throws Exception {

		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("displayPost", displayPost);
		data.put("postNum",     postNum);
		
		return sql.selectList(namespace + ".listPage", data);
	}
	
	
	

}















