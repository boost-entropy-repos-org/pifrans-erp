package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.SimpleUser;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

}
