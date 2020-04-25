package com.jp.kafka.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Column(nullable = false, length = 20)
    private String creditCardNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @Id
    @GeneratedValue
    private int orderId;

    @Column(nullable = false, length = 200)
    private String orderLocation;

    @Column(nullable = false, length = 20)
    private String orderNumber;
}