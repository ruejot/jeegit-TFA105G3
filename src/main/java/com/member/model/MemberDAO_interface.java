package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public abstract void insert(MemberVO memberBean);
    public abstract void update(MemberVO memberBean);
	public abstract void delete(Integer memberid);
//	select single data By PrimaryKey
	public abstract MemberVO select(Integer memberid);
	public abstract MemberVO selectByEmailAndPassword(String email, String password);
//	select all table data
	public abstract List<MemberVO> selectAll();
}
