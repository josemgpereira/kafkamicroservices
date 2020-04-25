package com.jp.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Column(nullable = false, length = 200)
    private String itemName;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;

    @Id
    @GeneratedValue
    private int orderItemId;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;
}