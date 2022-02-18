package com.memreply.model;

import java.sql.Timestamp;

public class MemReplyVO implements java.io.Serializable{
	private Integer reId;
	private Integer reArtId;
	private Integer reMemberId;
	private String re;
	private Timestamp time;
	
	public Integer getReId() {
		return reId;
	}
	public void setReId(Integer reId) {
		this.reId = reId;
	}
	public Integer getReArtId() {
		return reArtId;
	}
	public void setReArtId(Integer reArtId) {
		this.reArtId = reArtId;
	}
	public Integer getReMemberId() {
		return reMemberId;
	}
	public void setReMemberId(Integer reMemberId) {
		this.reMemberId = reMemberId;
	}
	public String getRe() {
		return re;
	}
	public void setRe(String re) {
		this.re = re;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}

