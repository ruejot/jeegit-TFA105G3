package com.order.model;

import java.util.List;

public class OrderService {
	
	private OrderDAO_interface dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}

	public OrderVO insertOrder(Integer orderId, Integer memberId, Integer busId, java.sql.Timestamp orderTime, Integer orderSum, Integer paymentId, Integer shippingId, Integer tracking, Integer orderStatus, String invoiceId, String receiver, String receiverAddr, String receiverPhone) {
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setOrderId(orderId);
		orderVO.setMemberId(memberId);
		orderVO.setBusId(busId);
		orderVO.setOrderTime(orderTime);
		orderVO.setOrderSum(orderSum);
		orderVO.setPaymentId(paymentId);
		orderVO.setShippingId(shippingId);
		orderVO.setTracking(tracking);
		orderVO.setOrderStatus(orderStatus);
		orderVO.setInvoiceId(invoiceId);
		orderVO.setReceiver(receiver);
		orderVO.setReceiverAddr(receiverAddr);
		orderVO.setReceiverPhone(receiverPhone);
		
		dao.insert(orderVO);
		
		return orderVO;
	}
	
public OrderVO updateOrder(Integer orderId, Integer memberId, Integer busId, Integer orderSum, Integer paymentId, Integer shippingId, Integer tracking, Integer orderStatus, String invoiceId, String receiver, String receiverAddr, String receiverPhone) {
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setOrderId(orderId);
		orderVO.setMemberId(memberId);
		orderVO.setBusId(busId);
		orderVO.setOrderSum(orderSum);
		orderVO.setPaymentId(paymentId);
		orderVO.setShippingId(shippingId);
		orderVO.setTracking(tracking);
		orderVO.setOrderStatus(orderStatus);
		orderVO.setInvoiceId(invoiceId);
		orderVO.setReceiver(receiver);
		orderVO.setReceiverAddr(receiverAddr);
		orderVO.setReceiverPhone(receiverPhone);
		
		dao.update(orderVO);
		
		return orderVO;
	}

	public void deleteOrder(Integer orderId) {
		dao.delete(orderId);
	}
	
	public OrderVO getOneByOrderId(Integer orderId) {
		return dao.findByOrderId(orderId);
	}
	
	public List<OrderVO> getOrdersByBusId(Integer busId) {
		return dao.findByBusId(busId);
	}
	
	public List<OrderVO> getOrdersByMemberId(Integer memberId) {
		return dao.findByMemberId(memberId);
	}
	
	public List<OrderVO> getAll() {
		return dao.getAll();
	}
}
