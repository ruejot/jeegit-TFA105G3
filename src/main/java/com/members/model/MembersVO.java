package com.members.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MembersVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 11 columns
	private Integer memberid;
	private String name;
	private String mobile;
	private String phone;
	private String address;
	private Timestamp date;
	private String email;
	private String password;
	private String nickname;
	private String intro;
	private byte[] photo;
	
	@Override
	public String toString() {
		return "com.members.model.MemberBean [" + memberid + "," + name + "," + mobile + "," + phone
				+ "," + address + "," + date + "," + email + "," + password
				+ "," + nickname + "," + intro + "]";
		// + "," + Arrays.toString(photo)
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getTimestamp() {
		return date;
	}

	public void setTimestamp(Timestamp date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
