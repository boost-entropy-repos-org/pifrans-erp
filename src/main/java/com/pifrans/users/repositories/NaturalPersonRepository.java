package com.pifrans.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.users.models.NaturalPerson;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {

}
