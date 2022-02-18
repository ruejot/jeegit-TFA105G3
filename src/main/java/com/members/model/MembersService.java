package com.members.model;

import java.util.List;

public class MembersService {
	private MembersDAO_interface dao;
	
	public MembersService() {
		dao = new MembersDAO();
	}
	
//Integer memberid, String name, String mobile, String phone,	String address,	java.sql.Date date,	String email, String password, String nickname, String intro, byte[] photo 
	
	public MembersVO insertMembers(String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO membersBean = new MembersVO();
		
		membersBean.setName(name);
		membersBean.setMobile(mobile);
		membersBean.setPhone(phone);
		membersBean.setAddress(address);
		membersBean.setDate(date);
		membersBean.setEmail(email);
		membersBean.setPassword(password);
		membersBean.setNickname(nickname);
		membersBean.setIntro(intro);
		membersBean.setPhoto(photo);
		
		dao.insert(membersBean);
		return membersBean;
	}
	
	public MembersVO updateMembers(Integer memberid, String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO membersBean = new MembersVO();
		
		membersBean.setMemberid(memberid);
		membersBean.setName(name);
		membersBean.setMobile(mobile);
		membersBean.setPhone(phone);
		membersBean.setAddress(address);
		membersBean.setDate(date);
		membersBean.setEmail(email);
		membersBean.setPassword(password);
		membersBean.setNickname(nickname);
		membersBean.setIntro(intro);
		membersBean.setPhoto(photo);
		
		dao.update(membersBean);
		return membersBean;
	}
	
	public void deleteMembers(Integer memberid) {
		dao.delete(memberid);
	}
	
	public MembersVO select(Integer memberid) {
		return dao.select(memberid);
	}
	
	public List<MembersVO> selectAll() {
		return dao.selectAll();
	}
	
}
