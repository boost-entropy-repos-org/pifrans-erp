package com.pifrans.modules.place.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "city")
public class City extends Place {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private List<Address> addresses;

	public City() {
	}

	public City(Long id, String name, String initials, State state) {
		super(id, name, initials);
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

}
