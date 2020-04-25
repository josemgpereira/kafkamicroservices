package com.jp.kafka.broker.consumer;

import com.jp.kafka.broker.message.DiscountMessage;
import com.jp.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "t.commodity.promotion")
public class PromotionListener {

    private static final Logger logger = LoggerFactory.getLogger(PromotionListener.class);

    @KafkaHandler
    public void listenDiscount(DiscountMessage message) {
        logger.info("Processing discount : {}", message);
    }

    @KafkaHandler
    public void listenPromotion(PromotionMessage message) {
        logger.info("Processing promotion : {}", message);
    }
}