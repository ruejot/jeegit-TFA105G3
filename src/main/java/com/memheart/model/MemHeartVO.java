package com.memheart.model;

import java.sql.Timestamp;

public class MemHeartVO implements java.io.Serializable{
	public Integer getHeArtId() {
		return heArtId;
	}
	public void setHeArtId(Integer heArtId) {
		this.heArtId = heArtId;
	}
	public Integer getHeMemberId() {
		return heMemberId;
	}
	public void setHeMemberId(Integer heMemberId) {
		this.heMemberId = heMemberId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	private Integer heArtId;
	private Integer heMemberId;
	private Timestamp time;
	
}

