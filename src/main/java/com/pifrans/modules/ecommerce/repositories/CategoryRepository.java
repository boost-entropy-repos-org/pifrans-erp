package com.pifrans.modules.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.ecommerce.models.Category;

@Repository("ecmCategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
