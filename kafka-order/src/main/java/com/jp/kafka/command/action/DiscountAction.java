package com.jp.kafka.command.action;

import com.jp.kafka.api.request.DiscountRequest;
import com.jp.kafka.broker.message.DiscountMessage;
import com.jp.kafka.broker.producer.DiscountProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DiscountAction {

    private final DiscountProducer producer;

    public void publishToKafka(DiscountRequest request) {
        var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
        producer.publish(message);
    }
}