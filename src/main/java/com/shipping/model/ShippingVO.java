package com.shipping.model;

public class ShippingVO implements java.io.Serializable{
	private Integer shippingId;
	private String shippingMethod;
	
	public ShippingVO() {
		super();
	}
	

	public ShippingVO(Integer shippingId, String shippingMethod) {
		super();
		this.shippingId = shippingId;
		this.shippingMethod = shippingMethod;
	}


	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	@Override
	public String toString() {
		return "ShippingVO [shippingId=" + shippingId + ", shippingMethod=" + shippingMethod + "]";
	}
	
	

}
