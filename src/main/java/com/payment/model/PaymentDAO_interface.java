package com.payment.model;

import java.util.List;

public interface PaymentDAO_interface {

		public void insert(PaymentVO paymentVO);

		public void update(PaymentVO paymentVO);

		public void delete(Integer paymentid);

		public PaymentVO findByPrimaryKey(Integer paymentid);

		public List<PaymentVO> getAll();

}