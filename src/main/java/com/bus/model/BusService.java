package com.bus.model;

import java.util.List;

public class BusService {
	private BusDAO_interface dao;
	
	public BusService() {
		dao = new BusDAO();
	}
	
//Integer busid, String name, String phone, String address, String taxid, java.sql.Date date, String email, String password, String intro, byte[] photo, String fb, String ig, String website, String paymentprovide
	
	//新增
	public BusVO insertBus(String name, String phone, String address, String taxid
			, java.sql.Date date, String email, String password, String intro
			, byte[] photo, String fb, String ig, String website, String paymentprovide) {
		
		BusVO busBean = new BusVO();
		
		busBean.setName(name);
		busBean.setPhone(phone);
		busBean.setAddress(address);
		busBean.setTaxid(taxid);
		busBean.setDate(date);
		busBean.setEmail(email);
		busBean.setPassword(password);
		busBean.setIntro(intro);
		busBean.setPhoto(photo);
		busBean.setFb(fb);
		busBean.setIg(ig);
		busBean.setWebsite(website);
		busBean.setPaymentprovide(paymentprovide);
		
		dao.insert(busBean);
		return busBean;
	}
	
	//修改
	public BusVO updateBus(Integer busid, String name, String phone, String address, String taxid
			, java.sql.Date date, String email, String password, String intro
			, byte[] photo, String fb, String ig, String website, String paymentprovide) {
		
		BusVO busBean = new BusVO();
		
		busBean.setBusid(busid);
		busBean.setName(name);
		busBean.setPhone(phone);
		busBean.setAddress(address);
		busBean.setTaxid(taxid);
		busBean.setDate(date);
		busBean.setEmail(email);
		busBean.setPassword(password);
		busBean.setIntro(intro);
		busBean.setPhoto(photo);
		busBean.setFb(fb);
		busBean.setIg(ig);
		busBean.setWebsite(website);
		busBean.setPaymentprovide(paymentprovide);
		
		dao.update(busBean);
		return busBean;
	}
	
	//刪除
	public void deleteBus(Integer busid) {
		dao.delete(busid);
	}
	
	//查詢
	public BusVO select(Integer busid) {
		return dao.select(busid);
	}
	
	//查詢全部
	public List<BusVO> selectAll() {
		return dao.selectAll();
	}
	

}
