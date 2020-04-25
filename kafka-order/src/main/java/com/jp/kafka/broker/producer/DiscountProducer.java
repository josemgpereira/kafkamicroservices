package com.jp.kafka.broker.producer;

import com.jp.kafka.broker.message.DiscountMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiscountProducer {

    private final KafkaTemplate<String, DiscountMessage> kafkaTemplate;

    public void publish(DiscountMessage message) {
        kafkaTemplate.send("t.commodity.promotion", message);
    }
}