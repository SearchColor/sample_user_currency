package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@Profile("dev")
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;


    @PostConstruct
    public void init() {

        User user1 = new User("user1" , "aa@aa.com");
        User user2 = new User("user2" , "bb@bb.com");

        BigDecimal exchangeRate1 = new BigDecimal(900);
        Currency currency1 = new Currency("USD" , exchangeRate1 , "$");

        BigDecimal exchangeRate2 = new BigDecimal(9);
        Currency currency2 = new Currency("JPY" , exchangeRate2 , "円");

        BigDecimal exchangeRate3 = new BigDecimal(-1500);
        Currency badCurrency = new Currency("BAD" , exchangeRate3 , "X");

        userRepository.save(user1);
        userRepository.save(user2);
        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(badCurrency);

        int invalidCount = currencyRepository.countBadCurrency();

        // 유효하지않는 currency 존재시 로그 기록
        if (invalidCount != 0){
            log.info("====== exist invalid currency =====");
        }



        log.info("===== Test Data Initialized =====");
    }

    @PreDestroy
    public void destroy() {
        this.disconnect();
    }

    // 애플리케이션 종료 시 호출
    public void disconnect() {
        log.info("====== close Initialized ======" );
    }

}