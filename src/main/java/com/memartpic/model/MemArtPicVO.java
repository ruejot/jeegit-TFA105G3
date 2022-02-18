package com.memartpic.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MemArtPicVO implements java.io.Serializable{
	
	private Integer blArtPicId;
	private Integer blArtId;
	private byte[] blArtPic;
	private Timestamp time;
	
	public Integer getBlArtPicId() {
		return blArtPicId;
	}
	public void setBlArtPicId(Integer blArtPicId) {
		this.blArtPicId = blArtPicId;
	}
	public Integer getBlArtId() {
		return blArtId;
	}
	public void setBlArtId(Integer blArtId) {
		this.blArtId = blArtId;
	}
	public byte[] getBlArtPic() {
		return blArtPic;
	}
	public void setBlArtPic(byte[] blArtPic) {
		this.blArtPic = blArtPic;
	}
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	}
	





