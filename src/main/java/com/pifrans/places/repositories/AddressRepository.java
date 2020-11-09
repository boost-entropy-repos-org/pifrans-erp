package com.pifrans.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.places.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
