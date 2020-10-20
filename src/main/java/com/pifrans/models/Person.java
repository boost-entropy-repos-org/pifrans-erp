package com.pifrans.models;

import java.util.List;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends User {
	private static final long serialVersionUID = 1L;

	private List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
