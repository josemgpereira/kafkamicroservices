package com.jp.kafka.command.service;

import com.jp.kafka.api.request.PromotionRequest;
import com.jp.kafka.command.action.PromotionAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionAction action;

    public void createPromotion(PromotionRequest request) {
        action.publishToKafka(request);
    }
}