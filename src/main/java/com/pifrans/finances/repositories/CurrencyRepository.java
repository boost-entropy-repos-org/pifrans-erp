package com.pifrans.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.places.models.Address;

@Repository
public interface CurrencyRepository extends JpaRepository<Address, Long> {

}
