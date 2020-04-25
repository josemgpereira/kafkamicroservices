package com.jp.kafka.command.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.jp.kafka.api.request.DiscountRequest;
import com.jp.kafka.command.action.DiscountAction;

@RequiredArgsConstructor
@Service
public class DiscountService {

    private final DiscountAction action;

    public void createDiscount(DiscountRequest request) {
        action.publishToKafka(request);
    }
}
