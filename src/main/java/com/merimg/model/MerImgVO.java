package com.merimg.model;

import java.io.Serializable;
import java.util.Arrays;

public class MerImgVO implements Serializable {
	private Integer imgid;
	private Integer merid;
	private String picname;
	private byte[] merpic;
	
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	@Override
	public String toString() {
		return "MerImgVO [imgid=" + imgid + ", merid=" + merid + ", merpic=" + Arrays.toString(merpic) + "]";
	}
	public Integer getImgid() {
		return imgid;
	}
	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}
	public Integer getMerid() {
		return merid;
	}
	public void setMerid(Integer merid) {
		this.merid = merid;
	}
	public byte[] getMerpic() {
		return merpic;
	}
	public void setMerpic(byte[] merpic) {
		this.merpic = merpic;
	}
}
