package com.board.dto;

import java.util.Date;

public class BoardVO {
	private	int		bno;		//게시물 번호
	private	String	title;		//게시물 제목
	private	String	content;	//게시물 내용
	private	String	writer;		//게시물 작성자
	private	Date	regDate;	//게시물 작성일자
	private	int		viewCnt;	//게시물 조회수
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	
}
