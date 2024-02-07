package com.example.amazon.service.impl;

import com.example.amazon.model.Order;
import com.example.amazon.repository.OrderRepository;
import com.example.amazon.service.spec.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
