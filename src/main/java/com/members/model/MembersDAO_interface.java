package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public abstract void insert(MembersVO memberBean);
    public abstract void update(MembersVO memberBean);
	public abstract void delete(Integer memberid);
	public abstract void updateMemberPW(MembersVO memberBean);
	
//	select single data By PrimaryKey
	public abstract MembersVO select(Integer memberid);
	public abstract MembersVO select(String email);	
	public abstract MembersVO selectByEmailAndPassword(String email, String password);
//	select 2 data By PrimaryKey	
	public abstract MembersVO selectMemberIDEmail(String email);
//	select all table data
	public abstract List<MembersVO> selectAll();
	public abstract MembersVO selectPassword(Integer memberid);
//	public abstract void updateMemberPW(Integer memberid,String password,String email,String name,String mobile);


}
