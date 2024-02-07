package com.example.amazon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TB_PRODUCT")
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @Column(name = "PRICE", columnDefinition = "double", nullable = false)
    private double price;
}
