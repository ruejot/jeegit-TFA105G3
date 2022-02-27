package com.members.model;

import java.util.List;

public class MembersService {
	private MembersDAO_interface dao;
	
	public MembersService() {
		dao = new MembersDAO();
	}
	
//Integer memberid, String name, String mobile, String phone,	String address,	java.sql.Date date,	String email, String password, String nickname, String intro, byte[] photo 
	
	//新增
	public MembersVO insertMember(String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO memberBean = new MembersVO();
		
		memberBean.setName(name);
		memberBean.setMobile(mobile);
		memberBean.setPhone(phone);
		memberBean.setAddress(address);
		memberBean.setDate(date);
		memberBean.setEmail(email);
		memberBean.setPassword(password);
		memberBean.setNickname(nickname);
		memberBean.setIntro(intro);
		memberBean.setPhoto(photo);
		
		dao.insert(memberBean);
		return memberBean;
	}
	
	//修改
	public MembersVO updateMember(Integer memberid, String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO memberBean = new MembersVO();
		
		memberBean.setMemberid(memberid);
		memberBean.setName(name);
		memberBean.setMobile(mobile);
		memberBean.setPhone(phone);
		memberBean.setAddress(address);
		memberBean.setDate(date);
		memberBean.setEmail(email);
		memberBean.setPassword(password);
		memberBean.setNickname(nickname);
		memberBean.setIntro(intro);
		memberBean.setPhoto(photo);
		
		dao.update(memberBean);
		return memberBean;
	}
	
	//刪除
	public void deleteMember(Integer memberid) {
		dao.delete(memberid);
	}
		
	//查詢
	public MembersVO select(Integer memberid) {
		return dao.select(memberid);
	}
	
	//查詢全部
	public List<MembersVO> selectAll() {
		return dao.selectAll();
	}
	
}
