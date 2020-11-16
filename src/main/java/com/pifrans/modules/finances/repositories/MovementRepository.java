package com.pifrans.modules.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.places.models.Address;

@Repository
public interface MovementRepository extends JpaRepository<Address, Long> {

}
