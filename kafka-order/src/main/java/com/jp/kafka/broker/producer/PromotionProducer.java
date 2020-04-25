package com.jp.kafka.broker.producer;

import com.jp.kafka.broker.message.PromotionMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PromotionProducer {

    private static final Logger logger = LoggerFactory.getLogger(PromotionProducer.class);

    private final KafkaTemplate<String, PromotionMessage> kafkaTemplate;

    public void publish(PromotionMessage message) {
        try {
            var sendResult = kafkaTemplate.send("t.commodity.promotion", message).get();

            logger.info("Send result success for message {}", sendResult.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error publishing {}, cause {}", message, e.getMessage());
        }
    }
}