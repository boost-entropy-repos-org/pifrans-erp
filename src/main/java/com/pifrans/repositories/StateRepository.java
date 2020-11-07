package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.places.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
