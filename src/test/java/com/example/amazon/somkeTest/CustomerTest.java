package com.example.amazon.somkeTest;

import com.example.amazon.controller.CustomerController;
import com.example.amazon.repository.CustomerRepository;
import com.example.amazon.service.spec.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerTest {
    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void contextLoads() {
        assertThat(customerController).isNotNull();
        assertThat(customerService).isNotNull();
        assertThat(customerRepository).isNotNull();
    }

}
