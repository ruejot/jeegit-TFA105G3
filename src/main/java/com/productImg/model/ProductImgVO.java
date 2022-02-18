package com.productImg.model;

import java.sql.Date;

public class ProductImgVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer imgid;
	private Integer busid;
	private byte[] merpic;
	private Date time;
	private Integer merid;
	
	public Integer getImgid() {
		return imgid;
	}
	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}
	public Integer getBusid() {
		return busid;
	}
	public void setBusid(Integer busid) {
		this.busid = busid;
	}
	public byte[] getMerpic() {
		return merpic;
	}
	public void setMerpic(byte[] merpic) {
		this.merpic = merpic;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getMerid() {
		return merid;
	}
	public void setMerid(Integer merid) {
		this.merid = merid;
	}
	
}
