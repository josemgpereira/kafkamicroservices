package com.jp.kafka.broker.consumer;

import com.jp.kafka.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    @KafkaListener(topics = "t.commodity.order")
    public void listen(OrderMessage message) {
        var totalItemAmount = message.getPrice() * message.getQuantity();
        logger.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}.",
                message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalItemAmount);
    }
}