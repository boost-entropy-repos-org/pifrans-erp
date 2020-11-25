package com.pifrans.modules.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.finance.models.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

}
