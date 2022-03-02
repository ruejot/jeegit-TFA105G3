package com.csdetail.model;

import java.io.Serializable;


public class CsDetailVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer caseid;
	private Integer memberid;
	private Integer busid;
	private Integer merid;
	private Integer orderid;
	private java.sql.Date casetime;
	private String feedback;
	private Integer replystatus;
	private String replycontent;
	private java.sql.Date replytime;
	
	
	@Override
	public String toString() {
		return "com.csdetail.model.CsDetailBean ["+ caseid+ ", "+ memberid+ ", "+ busid+ ", "+ merid+ ", "+ orderid+ ", "
				+ casetime+ ", "+ feedback+ ", "+ replystatus+ ", "+ replycontent+ ", "+ replytime+ "]";
	}
	
//	@Override
//	public String toString() {
//		return "CsDetailBean [caseid=" + caseid + ", memberid=" + memberid + ", busid=" + busid + ", casetime="
//				+ casetime + ", feedback=" + feedback + ", replystatus=" + replystatus + ", replycontent="
//				+ replycontent + ", replytime=" + replytime + "]";
//	}
	
	public Integer getCaseid() {
		return caseid;
	}
	public void setCaseid(Integer caseid) {
		this.caseid = caseid;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getBusid() {
		return busid;
	}
	public void setBusid(Integer busid) {
		this.busid = busid;
	}
	public Integer getMerid() {
		return merid;
	}
	public void setMerid(Integer merid) {
		this.merid = merid;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public java.sql.Date getCasetime() {
		return casetime;
	}
	public void setCasetime(java.sql.Date casetime) {
		this.casetime = casetime;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getReplystatus() {
		return replystatus;
	}
	public void setReplystatus(Integer replystatus) {
		this.replystatus = replystatus;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public java.sql.Date getReplytime() {
		return replytime;
	}
	public void setReplytime(java.sql.Date replytime) {
		this.replytime = replytime;
	}
}
