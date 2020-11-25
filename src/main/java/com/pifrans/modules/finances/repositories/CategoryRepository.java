package com.pifrans.modules.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.finances.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
