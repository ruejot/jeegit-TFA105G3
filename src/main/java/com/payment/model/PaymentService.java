package com.payment.model;

import java.util.List;


public class PaymentService {
private PaymentDAO_interface dao;
	
	public PaymentService() {
		dao = new PaymentDAO();
	}
	
	//預留給 Struts 2 用的
	public void addPayment(PaymentVO paymentVO) {
		dao.insert(paymentVO);
	}
	//預留給 Struts 2 用的
	public void updatePayment(PaymentVO paymentVO) {
		dao.update(paymentVO);
	}
	
	public void deletePayment(Integer paymentid) {
		dao.delete(paymentid);
	}
	
	public PaymentVO getOnePayment(Integer paymentid) {
		return dao.findByPrimaryKey(paymentid);
	}
	
	public List<PaymentVO> getAll(){
		return dao.getAll();
	}

}
