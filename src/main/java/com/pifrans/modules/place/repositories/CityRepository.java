package com.pifrans.modules.place.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.place.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
