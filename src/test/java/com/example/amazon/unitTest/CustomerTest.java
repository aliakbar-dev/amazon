package com.example.amazon.unitTest;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.dto.CustomerResponseDTO;
import com.example.amazon.model.Customer;
import com.example.amazon.repository.CustomerRepository;
import com.example.amazon.service.impl.CustomerServiceImpl;
import com.example.amazon.service.spec.CustomerService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {

    @Test
    void availableAndValidId_findById_returnCustomerSuccessfully() {
        final Customer customer = new Customer();
        customer.setId("11111");
        customer.setDescription("test");
        customer.setEmail("test@test.com");
        customer.setFirstName("firstName");
        customer.setLastName("lastName");

        final CustomerRepository customerRepository = mock(CustomerRepository.class);
        final CustomerService customerService = new CustomerServiceImpl(customerRepository);
        when(customerRepository.findById(any(String.class))).thenReturn(Optional.of(customer));

        final Optional<Customer> returnedCustomerOptional = customerService.findById("11111");
        assertThat(returnedCustomerOptional).isNotNull();
        assertThat(returnedCustomerOptional.isPresent()).isTrue();
    }

    @Test
    void unavailableAndValidId_findById_returnEmptyOptionalSuccessfully() {
        final CustomerRepository customerRepository = mock(CustomerRepository.class);
        final CustomerService customerService = new CustomerServiceImpl(customerRepository);
        when(customerRepository.findById(any(String.class))).thenReturn(Optional.empty());

        final Optional<Customer> returnedCustomerOptional = customerService.findById("11111");
        assertThat(returnedCustomerOptional).isNotNull();
        assertThat(returnedCustomerOptional.isPresent()).isFalse();
    }

    @Test
    void normalAndValidCustomer_saveCustomer_returnCustomerSuccessfully() {
        final Customer customer = new Customer();
        customer.setId("11111");
        customer.setDescription("test");
        customer.setEmail("test@test.com");
        customer.setFirstName("firstName");
        customer.setLastName("lastName");

        final CustomerRepository customerRepository = mock(CustomerRepository.class);
        final CustomerService customerService = new CustomerServiceImpl(customerRepository);
        final CustomerRequestDTO CustomerRequestDTO = new CustomerRequestDTO("test", "test@test.com", "firstName", "lastName");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        final CustomerResponseDTO customerResponseDTO = customerService.save(CustomerRequestDTO);
        assertThat(customerResponseDTO).isNotNull();
        assertThat(customerResponseDTO.firstName()).isSameAs(CustomerRequestDTO.firstName()).isSameAs(customer.getFirstName());
    }

    @Test
    void CustomerWithNullEmail_saveCustomer_failWithAppropriateMessage() {
        final CustomerRequestDTO CustomerRequestDTO = new CustomerRequestDTO("test", null, "firstName", "lastName");
        final CustomerService customerService = mock(CustomerService.class);
        given(customerService.save(CustomerRequestDTO)).willThrow(new ConstraintViolationException("email must not be null", null));

        final ConstraintViolationException constraintViolationException = assertThrowsExactly(ConstraintViolationException.class, () -> customerService.save(CustomerRequestDTO));
        assertThat(constraintViolationException).isNotNull();
        assertThat(constraintViolationException.getMessage()).isEqualTo("email must not be null");
    }

    @Test
    void CustomerWithInvalidEmail_saveCustomer_failWithAppropriateMessage() {
        final CustomerRequestDTO CustomerRequestDTO = new CustomerRequestDTO("test", "invalid email", "firstName", "lastName");
        final CustomerService customerService = mock(CustomerService.class);
        given(customerService.save(CustomerRequestDTO)).willThrow(new ConstraintViolationException("must be a well-formed email address", null));

        final ConstraintViolationException constraintViolationException = assertThrowsExactly(ConstraintViolationException.class, () -> customerService.save(CustomerRequestDTO));
        assertThat(constraintViolationException).isNotNull();
        assertThat(constraintViolationException.getMessage()).isEqualTo("must be a well-formed email address");
    }

}