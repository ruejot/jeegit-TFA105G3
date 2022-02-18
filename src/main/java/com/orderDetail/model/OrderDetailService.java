package com.orderDetail.model;

import java.util.List;

public class OrderDetailService {
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService () {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO insertOrderDetail(Integer orderId, Integer merId, Integer qty, Integer unitPrice, Integer ranking, String comment) {

		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setMerId(merId);
		orderDetailVO.setQty(qty);
		orderDetailVO.setUnitPrice(unitPrice);
		orderDetailVO.setRanking(ranking);
		orderDetailVO.setComment(comment);
		dao.insert(orderDetailVO);

		return orderDetailVO;
	}

	public OrderDetailVO updateOrderDetail(Integer orderId, Integer merId, Integer qty, Integer unitPrice, Integer ranking, String comment) {

		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setMerId(merId);
		orderDetailVO.setQty(qty);
		orderDetailVO.setUnitPrice(unitPrice);
		orderDetailVO.setRanking(ranking);
		orderDetailVO.setComment(comment);
		dao.update(orderDetailVO);

		return orderDetailVO;
	}
	
	public List<OrderDetailVO> getOrdersByOrderId(Integer orderId) {
		return dao.findByOrderId(orderId);
	}
	
	public List<OrderDetailVO> getOrdersByMerId(Integer merId) {
		return dao.findByMerId(merId);
	}
	
	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
}
