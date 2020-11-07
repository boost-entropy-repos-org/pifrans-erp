package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.places.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
