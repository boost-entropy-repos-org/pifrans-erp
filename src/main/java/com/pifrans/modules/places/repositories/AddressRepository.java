package com.pifrans.modules.places.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.places.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
