package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.LegalEntity;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

}
