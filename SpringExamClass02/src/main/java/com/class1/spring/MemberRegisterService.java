package com.class1.spring;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;


public class MemberRegisterService {
	//Resource는 name에 해당하는 빈 객체를 찾는다.
	//아래는 필드 주입방식인데 Autowired와 Qualifier를 합친 것과 같이 생각할 수 있다.
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	//생성자를 통해 MemberRegisterService 가 의존(Dependency)하고 있는
	//MemberDao 객체를 주입(Injection) 받는다. 
	@Autowired(required=false)
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public MemberRegisterService() {}
	
	public void regist(RegisterRequest req) {
		//이메일로 회원 데이터(Member)조회
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) { //같은 이메일을 누군가 이미 사용하고 있다면
			throw new AlreadyExistingMemberException("중복 이메일입니다." + req.getEmail());
		}
		//같은 이메일을 가진 회원이 없다면 DB에 데이터를 저장한다.
		Member newMember = new Member(
			req.getEmail(), req.getPassword(), req.getName(), new Date());
		memberDao.insert(newMember);
	}

}














