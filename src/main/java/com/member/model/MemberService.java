package com.member.model;

import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
//Integer memberid, String name, String mobile, String phone,	String address,	java.sql.Date date,	String email, String password, String nickname, String intro, byte[] photo 
	
	//新增
	public MemberVO insertMember(String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MemberVO memberBean = new MemberVO();
		
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
	public MemberVO updateMember(Integer memberid, String name
			, String mobile, String phone,	String address,	java.sql.Date date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MemberVO memberBean = new MemberVO();
		
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
	public MemberVO select(Integer memberid) {
		return dao.select(memberid);
	}
	
	//查詢全部
	public List<MemberVO> selectAll() {
		return dao.selectAll();
	}
	
}
