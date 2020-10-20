package com.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
