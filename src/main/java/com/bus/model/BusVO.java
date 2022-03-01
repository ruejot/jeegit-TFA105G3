package com.bus.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class BusVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//14 columns
	private Integer busid;
	private String name;
	private String phone;
	private String address;
	private String taxid;
	private Timestamp date;
	private String email;
	private String password;
	private String intro;
	private byte[] photo;
	private String fb;
	private String ig;
	private String website;
	private String paymentprovide;
	
	@Override
	public String toString() {
		return "com.bus.model.BusBean [" + busid + "," + name + "," + phone + "," + address 
				+ "," + taxid + "," + date + "," + email + "," + password + "," + intro
				+ "," + fb + "," + ig + "," + website + "," + paymentprovide + "]";
		// + "," + Arrays.toString(photo)
	}

	public Integer getBusid() {
		return busid;
	}

	public void setBusid(Integer busid) {
		this.busid = busid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
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

	public String getFb() {
		return fb;
	}

	public void setFb(String fb) {
		this.fb = fb;
	}

	public String getIg() {
		return ig;
	}

	public void setIg(String ig) {
		this.ig = ig;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPaymentprovide() {
		return paymentprovide;
	}

	public void setPaymentprovide(String paymentprovide) {
		this.paymentprovide = paymentprovide;
	}

	
	
}
