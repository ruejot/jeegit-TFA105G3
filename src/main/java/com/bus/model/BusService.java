package com.bus.model;

import java.util.List;

public class BusService {
	private BusBeanDAO_interface dao;
	
	public BusService() {
		dao = new BusDAO();
	}
	
//Integer busid, String name, String phone, String address, String taxid, java.sql.Date date, String email, String password, String intro, byte[] photo, String fb, String ig, String website, String paymentprovide
	
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
	
	public void deleteBus(Integer busid) {
		dao.delete(busid);
	}
	
	public BusVO select(Integer busid) {
		return dao.select(busid);
	}
	
	public List<BusVO> selectAll() {
		return dao.selectAll();
	}

}
