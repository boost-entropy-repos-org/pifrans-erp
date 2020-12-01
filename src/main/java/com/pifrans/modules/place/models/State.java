package com.pifrans.modules.place.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "plc_state")
public class State extends Place {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@JsonIgnore
	@OneToMany(mappedBy = "state")
	private List<City> cities;

	public State() {
	}

	public State(Long id, String name, String initials, Country country) {
		super(id, name, initials);
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public void addCity(City city) {
		cities.add(city);
	}
}
