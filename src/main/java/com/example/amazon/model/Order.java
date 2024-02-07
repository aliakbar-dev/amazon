package com.example.amazon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "TB_ORDER")
@IdClass(OrderId.class)
public class Order {
    @Id
    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Product product;

    @Column(name = "COUNT", nullable = false)
    private int count;
}
