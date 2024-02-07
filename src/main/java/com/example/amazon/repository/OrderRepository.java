package com.example.amazon.repository;

import com.example.amazon.model.Order;
import com.example.amazon.model.OrderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, OrderId> {
}
