package com.jp.kafka.api.server;

import com.jp.kafka.api.request.PromotionRequest;
import com.jp.kafka.command.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/promotion")
public class PromotionApi {

    private final PromotionService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(@RequestBody PromotionRequest request) {
        service.createPromotion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request.getPromotionCode());
    }
}