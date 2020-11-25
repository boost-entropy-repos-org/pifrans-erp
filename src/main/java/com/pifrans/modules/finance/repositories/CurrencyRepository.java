package com.pifrans.modules.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.finance.models.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
