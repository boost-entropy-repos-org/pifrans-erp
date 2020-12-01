package com.pifrans.modules.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ecm_payment_card")
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer installments;

	public PaymentCard() {
	}

	public PaymentCard(Long id, Integer status, Order order, Integer installments) {
		super(id, status, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

}
