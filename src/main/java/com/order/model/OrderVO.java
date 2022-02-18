package com.order.model;

import java.sql.Timestamp;

public class OrderVO implements java.io.Serializable{
	private Integer orderId;
	private Integer memberId;
	private Integer busId;
	private Timestamp orderTime;
	private Integer orderSum;
	private Integer paymentId;
	private Integer shippingId;
	private Integer tracking;
	private Integer orderStatus;
	private String invoiceId;
	private String receiver;
	private String receiverAddr;
	private String receiverPhone;
	
	public OrderVO() {
		super();
	}

	public OrderVO(Integer orderId, Integer memberId, Integer busId, Timestamp orderTime, Integer orderSum,
			Integer paymentId, Integer shippingId, Integer tracking, Integer orderStatus, String invoiceId,
			String receiver, String receiverAddr, String receiverPhone) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.busId = busId;
		this.orderTime = orderTime;
		this.orderSum = orderSum;
		this.paymentId = paymentId;
		this.shippingId = shippingId;
		this.tracking = tracking;
		this.orderStatus = orderStatus;
		this.invoiceId = invoiceId;
		this.receiver = receiver;
		this.receiverAddr = receiverAddr;
		this.receiverPhone = receiverPhone;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(Integer orderSum) {
		this.orderSum = orderSum;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public Integer getTracking() {
		return tracking;
	}

	public void setTracking(Integer tracking) {
		this.tracking = tracking;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", memberId=" + memberId + ", busId=" + busId + ", orderTime="
				+ orderTime + ", orderSum=" + orderSum + ", paymentId=" + paymentId + ", shippingId=" + shippingId
				+ ", tracking=" + tracking + ", orderStatus=" + orderStatus + ", invoiceId=" + invoiceId + ", receiver="
				+ receiver + ", receiverAddr=" + receiverAddr + ", receiverPhone=" + receiverPhone + "]";
	}
	
}
