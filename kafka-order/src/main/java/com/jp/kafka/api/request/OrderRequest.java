package com.jp.kafka.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private String creditCardNumber;
    private List<OrderItemRequest> items;
    private String orderLocation;
}