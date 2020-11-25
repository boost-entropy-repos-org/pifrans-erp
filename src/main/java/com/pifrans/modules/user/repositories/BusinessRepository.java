package com.pifrans.modules.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.user.models.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
