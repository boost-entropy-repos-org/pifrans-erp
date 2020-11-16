package com.pifrans.modules.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.places.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
