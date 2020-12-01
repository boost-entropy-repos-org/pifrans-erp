package com.pifrans.modules.ecommerce.enums;

public enum StatusPayment {
	PENDING(1, "Pendente"), PAID(2, "Pago"), CANCELED(3, "Cancelado");

	private int id;
	private String description;

	private StatusPayment(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static StatusPayment toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (StatusPayment x : StatusPayment.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + id);
	}
}
