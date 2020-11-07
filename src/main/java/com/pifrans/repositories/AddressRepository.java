package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.places.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
