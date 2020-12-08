package com.pifrans.modules.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pifrans.global.models.User;
import com.pifrans.modules.ecommerce.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Transactional(readOnly = true)
	Page<Order> findByUser(User user, Pageable pageRequest);
}
