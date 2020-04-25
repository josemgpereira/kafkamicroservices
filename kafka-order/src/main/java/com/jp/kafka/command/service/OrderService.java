package com.jp.kafka.command.service;

import com.jp.kafka.api.request.OrderRequest;
import com.jp.kafka.command.action.OrderAction;
import com.jp.kafka.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAction action;

    public String saveOrder(OrderRequest request) {
        Order order = action.convertToOrder(request);
        action.saveToDatabase(order);
        order.getItems().forEach(action::publishToKafka);
        return order.getOrderNumber();
    }
}