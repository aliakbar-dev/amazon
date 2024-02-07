package com.example.amazon.service.impl;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.dto.CustomerResponseDTO;
import com.example.amazon.mapper.CustomerMapper;
import com.example.amazon.model.Customer;
import com.example.amazon.repository.CustomerRepository;
import com.example.amazon.service.spec.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Validated
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public CustomerResponseDTO save(@Valid CustomerRequestDTO customerRequestDTO) {
        return CustomerMapper.INSTANCE.CustomerToCustomerResponseDTO(customerRepository.save(CustomerMapper.INSTANCE.CustomerRequestDTOToCustomer(customerRequestDTO)));
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

}
