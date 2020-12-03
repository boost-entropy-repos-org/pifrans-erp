package com.pifrans.modules.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.ecommerce.models.PaymentCard;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

}
