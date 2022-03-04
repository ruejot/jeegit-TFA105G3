package com.members.model;

import java.sql.Timestamp;
import java.util.List;

public class MembersService {
	private MembersDAO_interface dao;
	
	public MembersService() {
		dao = new MembersDAO();
	}
	
//Integer memberid, String name, String mobile, String phone,	String address,	Timestamp date,	String email, String password, String nickname, String intro, byte[] photo 
	
	//新增
	public MembersVO insertMember(String name
			, String mobile, String phone,	String address,	Timestamp date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO memberBean = new MembersVO();
		
		memberBean.setName(name);
		memberBean.setMobile(mobile);
		memberBean.setPhone(phone);
		memberBean.setAddress(address);
		memberBean.setTimestamp(date);
		memberBean.setEmail(email);
		memberBean.setPassword(password);
		memberBean.setNickname(nickname);
		memberBean.setIntro(intro);
		memberBean.setPhoto(photo);
		
		dao.insert(memberBean);
		return memberBean;
	}
	
	//註冊
	public MembersVO registerMember(String name
			, String mobile,Timestamp date, String email, String password) {
		
		MembersVO memberBean = new MembersVO();
		
		memberBean.setName(name);
		memberBean.setMobile(mobile);
		memberBean.setTimestamp(date);
		memberBean.setEmail(email);
		memberBean.setPassword(password);
		
		dao.insert(memberBean);
		return memberBean;
	}	
	
	
	
	//修改
	public MembersVO updateMember(String name
			, String mobile, String phone,	String address,	Timestamp date
			, String email, String password, String nickname, String intro, byte[] photo) {
		
		MembersVO memberBean = new MembersVO();
		
//		memberBean.setMemberid(memberid);
		memberBean.setName(name);
		memberBean.setMobile(mobile);
		memberBean.setPhone(phone);
		memberBean.setAddress(address);
		memberBean.setTimestamp(date);
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
	
//	//查詢密碼
//	public MembersVO select(String password) {
//		return dao.select(password);
//	}
	
	//查詢全部
	public List<MembersVO> selectAll() {
		return dao.selectAll();
	}
	
}
