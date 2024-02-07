package com.example.amazon.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderId implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID", nullable = false)
    private Product product;
}
