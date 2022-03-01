package com.cart.model;

public class Products implements java.io.Serializable {
	public Products() {
		merId=0;
		busId=0;
		name="";
		price=0;
		qty=0;
	}
	
	private Integer merId;
	private Integer busId;
	private String name;
	private Integer price;
	private Integer qty;
	
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
