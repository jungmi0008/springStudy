package com.class1.spring;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;


public class MemberRegisterService {
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	@Autowired(required=false)
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public MemberRegisterService() {}
	
	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) {
			throw new AlreadyExistingMemberException("중복 이메일입니다." + req.getEmail());
		}

		Member newMember = new Member(
			req.getEmail(), req.getPassword(), req.getName(), new Date());
		memberDao.insert(newMember);
	}

}














