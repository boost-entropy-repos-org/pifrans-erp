package com.pifrans.modules.ecommerce.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pifrans.global.models.User;

@Entity
@Table(name = "ecm_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order")
	private Set<Cart> carts = new HashSet<>();

	public Order() {
	}

	public Order(Long id, Date instant, User user) {
		super();
		this.id = id;
		this.instant = instant;
		this.user = user;
	}

	public double getTotalValue() {
		double sum = 0.0;
		for (Cart cart : carts) {
			sum = sum + cart.getSubTotal();
		}
		return sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getUser().getNickname());
		builder.append(", Solicitação do pagamento: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetalhes:\n");

		for (Cart cart : getCarts()) {
			builder.append(cart.toString());
		}

		builder.append("Valor total: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	}

}
