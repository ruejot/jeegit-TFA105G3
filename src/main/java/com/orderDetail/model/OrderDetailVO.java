package com.orderDetail.model;

public class OrderDetailVO implements java.io.Serializable{
	private Integer orderId;
	private Integer merId;
	private Integer qty;
	private Integer unitPrice;
	private Integer ranking;
	private String comment;
	
	public OrderDetailVO() {
		super();
	}

	public OrderDetailVO(Integer orderId, Integer merId, Integer qty, Integer unitPrice, Integer ranking,
			String comment) {
		super();
		this.orderId = orderId;
		this.merId = merId;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.ranking = ranking;
		this.comment = comment;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMerId() {
		return merId;
	}

	public void setMerId(Integer merId) {
		this.merId = merId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [orderId=" + orderId + ", merId=" + merId + ", qty=" + qty + ", unitPrice=" + unitPrice
				+ ", ranking=" + ranking + ", comment=" + comment + "]";
	}
	
	

}