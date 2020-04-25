package com.jp.kafka.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jp.kafka.broker.message.OrderReplyMessage;

@Service
public class OrderReplyListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderReplyListener.class);

    @KafkaListener(topics = "t.commodity.order-reply")
    public void listen(OrderReplyMessage message) {
        logger.info("Reply message received: {}", message);
    }
}