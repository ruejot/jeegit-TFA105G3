package com.shipping.model;

import java.util.List;

public interface ShippingDAO_interface {
	public void insert(ShippingVO shippingVO);
	public void update(ShippingVO shippingVO);
	public void delete(Integer shippingId);
	public ShippingVO findByShippingId(Integer shippingId);
	public List<ShippingVO> getAll();

}
