package com.jp.kafka.broker.producer;

import com.jp.kafka.broker.message.OrderMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
        int surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
        List<Header> headers = new ArrayList<>();
        var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());
        headers.add(surpriseBonusHeader);
        return new ProducerRecord<String, OrderMessage>("t.commodity.order", null, message.getOrderNumber(), message, headers);
    }

    public void publish(OrderMessage message) {
        var producerRecord = buildProducerRecord(message);
        kafkaTemplate.send(producerRecord).addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {
        //kafkaTemplate.send("t.commodity.order", message.getOrderNumber(), message).addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {
            @Override
            public void onSuccess(SendResult<String, OrderMessage> result) {
                logger.info("Order {}, item {} published successfully", message.getOrderNumber(), message.getItemName());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Order {}, item {} failed to publish, because {}", message.getOrderNumber(), message.getItemName(), ex.getMessage());
            }
        });

        logger.info("Published successfully");
    }
}