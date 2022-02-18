package com.memsavedart.model;

import java.sql.Timestamp;

public class MemSavedArtVO implements java.io.Serializable{
	private Integer savId;
	private Integer savMemberId;
	private Integer savArtId;
	private Timestamp time;
	public Integer getSavId() {
		return savId;
	}
	public void setSavId(Integer savId) {
		this.savId = savId;
	}
	public Integer getSavMemberId() {
		return savMemberId;
	}
	public void setSavMemberId(Integer savMemberId) {
		this.savMemberId = savMemberId;
	}
	public Integer getSavArtId() {
		return savArtId;
	}
	public void setSavArtId(Integer savArtId) {
		this.savArtId = savArtId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}

