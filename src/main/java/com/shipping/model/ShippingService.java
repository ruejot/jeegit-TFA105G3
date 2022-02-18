package com.shipping.model;

import java.util.List;

public class ShippingService {
	private ShippingDAO_interface dao;
	
	public ShippingService() {
		dao = new ShippingDAO();
	}
	
	public ShippingVO insertShipping(Integer shippingId, String shippingMethod) {
		
		ShippingVO shippingVO = new ShippingVO();
		
		shippingVO.setShippingId(shippingId);
		shippingVO.setShippingMethod(shippingMethod);
		dao.insert(shippingVO);
		
		return shippingVO;
	}
	
	public ShippingVO updateShipping(Integer shippingId, String shippingMethod) {
		
		ShippingVO shippingVO = new ShippingVO();
		
		shippingVO.setShippingId(shippingId);
		shippingVO.setShippingMethod(shippingMethod);
		dao.update(shippingVO);
		
		return shippingVO;
	}
	
	public void deleteShipping(Integer shippingId) {
		dao.delete(shippingId);
	}
	
	public ShippingVO getOneShipping(Integer shippingId) {
		return dao.findByShippingId(shippingId);
	}
	
	public List<ShippingVO> getAll() {
		return dao.getAll();
	}
}
