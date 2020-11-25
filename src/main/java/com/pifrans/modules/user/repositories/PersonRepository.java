package com.pifrans.modules.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.user.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
