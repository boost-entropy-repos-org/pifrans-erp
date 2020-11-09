package com.pifrans.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.places.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
