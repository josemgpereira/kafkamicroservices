package com.jp.kafka.command.action;

import com.jp.kafka.api.request.OrderItemRequest;
import com.jp.kafka.api.request.OrderRequest;
import com.jp.kafka.broker.message.OrderMessage;
import com.jp.kafka.broker.producer.OrderProducer;
import com.jp.kafka.entity.Order;
import com.jp.kafka.entity.OrderItem;
import com.jp.kafka.repository.OrderItemRepository;
import com.jp.kafka.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAction {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderProducer producer;

    public Order convertToOrder(OrderRequest request) {
        var result = new Order();
        result.setCreditCardNumber(request.getCreditCardNumber());
        result.setOrderLocation(request.getOrderLocation());
        result.setOrderDateTime(LocalDateTime.now());
        result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());
        List<OrderItem> items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
        items.forEach(item -> item.setOrder(result));
        result.setItems(items);
        return result;
    }

    private OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
        var result = new OrderItem();
        result.setItemName(itemRequest.getItemName());
        result.setPrice(itemRequest.getPrice());
        result.setQuantity(itemRequest.getQuantity());
        return result;
    }

    public void saveToDatabase(Order order) {
        orderRepository.save(order);
        order.getItems().forEach(orderItemRepository::save);
    }

    public void publishToKafka(OrderItem orderItem) {
        var orderMessage = new OrderMessage();
        orderMessage.setItemName(orderItem.getItemName());
        orderMessage.setPrice(orderItem.getPrice());
        orderMessage.setQuantity(orderItem.getQuantity());
        orderMessage.setOrderDateTime(orderItem.getOrder().getOrderDateTime());
        orderMessage.setOrderLocation(orderItem.getOrder().getOrderLocation());
        orderMessage.setOrderNumber(orderItem.getOrder().getOrderNumber());
        orderMessage.setCreditCardNumber(orderItem.getOrder().getCreditCardNumber());
        producer.publish(orderMessage);
    }
}