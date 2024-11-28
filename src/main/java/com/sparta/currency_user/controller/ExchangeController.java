package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.dto.exchange.ExchangeRequestDto;
import com.sparta.currency_user.dto.exchange.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;


    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange(@RequestBody ExchangeRequestDto requestDto){
        return ResponseEntity.ok().body(exchangeService.save(requestDto.getUserId(), requestDto.getCurrencyId(), requestDto.getAmountInKrw()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> findExchange(@PathVariable Long id) {
        return ResponseEntity.ok().body(exchangeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> cancelledExchange(@PathVariable Long id){
        exchangeService.cancelledExchange(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}