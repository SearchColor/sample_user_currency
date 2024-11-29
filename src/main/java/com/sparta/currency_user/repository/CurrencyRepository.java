package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {


    @Query(value = "select count (cu .id) from Currency cu where cu.exchangeRate < 0 " +
            "or cu.exchangeRate > 2000")
    Integer countBadCurrency ();
}
