package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.places.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
