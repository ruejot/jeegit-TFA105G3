package com.orderDetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public List<OrderDetailVO> findByOrderId(Integer orderId);
	public List<OrderDetailVO> findByMerId(Integer merId);
	public List<OrderDetailVO> getAll();

}
