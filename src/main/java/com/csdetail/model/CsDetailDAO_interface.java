package com.csdetail.model;

import java.util.List;
//import java.util.Set;

public interface CsDetailDAO_interface {
	public abstract void insert(CsDetailVO csDetailBean);
    public abstract void update(CsDetailVO csDetailBean);
	public abstract void delete(Integer caseid);
//	select single data By PrimaryKey_caseid
	public abstract CsDetailVO selectByCaseId(Integer caseid);
//	select all table data
	public abstract List<CsDetailVO> selectAll();
//	plan to selectCsDetailByMemberid
//	public Set<CsDetailBean> selectCsDetailByMemberid(Integer memberid);
}
