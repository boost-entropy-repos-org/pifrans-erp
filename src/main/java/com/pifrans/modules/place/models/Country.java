package com.pifrans.modules.place.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plc_country")
public class Country extends Place {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private List<State> states;

	public Country() {
	}

	public Country(Long id, String name, String initials) {
		super(id, name, initials);
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public void addState(State state) {
		states.add(state);
	}
}
