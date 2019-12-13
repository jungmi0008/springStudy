package com.class1.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//설정 메서드를 이용해서 의존 객체를 주입받는다.
public class MemberInfoPrinter {
	
	@Autowired(required=false)
	private	MemberDao		memDao;
	private	MemberPrinter	printer;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao	= memberDao;
	}
	
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터가 없습니다.");
		}
		printer.print(member);
		System.out.println();
	}
}


















