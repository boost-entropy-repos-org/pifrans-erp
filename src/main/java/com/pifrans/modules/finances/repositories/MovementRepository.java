package com.pifrans.modules.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.finances.models.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

}
