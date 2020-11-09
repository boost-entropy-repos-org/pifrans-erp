package com.pifrans.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.places.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
