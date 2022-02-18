package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public abstract void insert(MembersVO membersBean);
    public abstract void update(MembersVO membersBean);
	public abstract void delete(Integer memberid);
//	select single data By PrimaryKey
	public abstract MembersVO select(Integer memberid);
//	select all table data
	public abstract List<MembersVO> selectAll();
}
