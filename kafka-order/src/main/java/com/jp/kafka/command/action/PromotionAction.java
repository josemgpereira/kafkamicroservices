package com.jp.kafka.command.action;

import com.jp.kafka.api.request.PromotionRequest;
import com.jp.kafka.broker.message.PromotionMessage;
import com.jp.kafka.broker.producer.PromotionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionAction {

    private final PromotionProducer producer;

    public void publishToKafka(PromotionRequest request) {
        var message = new PromotionMessage(request.getPromotionCode());
        producer.publish(message);
    }

}
