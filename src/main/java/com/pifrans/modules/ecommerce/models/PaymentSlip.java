package com.pifrans.modules.ecommerce.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ecm_payment_slip")
public class PaymentSlip extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expirationDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;

	public PaymentSlip() {
	}

	public PaymentSlip(Long id, Integer status, Order order, Date expirationDate, Date paymentDate) {
		super(id, status, order);
		this.expirationDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
