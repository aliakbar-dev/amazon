package com.example.amazon.service.spec;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.dto.CustomerResponseDTO;
import com.example.amazon.model.Customer;
import jakarta.validation.Valid;

import java.util.Optional;

public interface CustomerService {

    CustomerResponseDTO save(@Valid CustomerRequestDTO customerRequestDTO);

    Optional<Customer> findById(String id);

}
