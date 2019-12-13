package com.class1.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//설정 메서드를 이용해서 의존 객체를 주입받는다.
public class MemberInfoPrinter {
	
	/*
	 * 주입에는
	 * 필드주입 생성자주입 setter주입이 있다.
	 * 
	 * Autowired를 필드위에 사용하는 경우
	 * MemberInfoPrinter의 기본생성자를 기본으로 객체를 주입하는 것과 같다.
	 * 따라서 오버로딩된 생성자가 MemberInfoPrinter에 있다면
	 * 기본생성자를 명시해주어야만 Autowired를 필드에 쓸 수 있다.
	 * 
	 * 필드주입을 지양해야한다.(심화문제)
	 * 필드주입의 원리는 선언된 필드들을  자동으로 주입해준다는 것이다.
	 */
//	@Autowired(required=false)
	private	MemberDao		memDao;
	private	MemberPrinter	printer;
	
	//아래에 injectDependency2를 해주기 때문에 필요 없음
//	@Autowired
//	public void setMemberDao(MemberDao memberDao) {
//		this.memDao	= memberDao;
//	}
//	
//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
	
	//메소드
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터가 없습니다.");
		}
		printer.print(member);
		System.out.println();
	}
	
//	@Autowired
//	public void injectDependency(MemberDao memDao, MemberPrinter printer) {
//		this.memDao = memDao;
//		this.printer = printer;
//	}
	
//	파라미터가 1개 이상의 경우에는 어떤 생성자에 대한 annotation 알수 있게 끔 한다.
//	Autowired의 경우에는 이전의 빈에서 의존성을 주입할때 set이라는 이름만 써야하는 한계를 극복한다.
//	요약하자면 Autowired를 쓰면 아래와 같이 메소드에 주입이 가능해진다.
//	이 방식도 setter주입인데 이름만 다른것뿐이다.
	@Autowired
	public void injectDependency2(MemberDao memDao, @Qualifier("printer2")MemberPrinter printer) {
		this.memDao = memDao;
		this.printer = printer;
	}
	
	
}


















