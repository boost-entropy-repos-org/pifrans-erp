package com.pifrans.models.places;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends Place {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
