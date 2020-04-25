package com.jp.kafka.api.server;

import com.jp.kafka.api.request.OrderRequest;
import com.jp.kafka.api.response.OrderResponse;
import com.jp.kafka.command.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderApi {

    private final OrderService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        String orderNumber = service.saveOrder(request);
        OrderResponse orderResponse = new OrderResponse(orderNumber);
        return ResponseEntity.ok().body(orderResponse);
    }
}