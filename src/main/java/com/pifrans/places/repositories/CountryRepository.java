package com.pifrans.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.places.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
