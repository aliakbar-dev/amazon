package com.example.amazon.controller;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.dto.CustomerResponseDTO;
import com.example.amazon.exception.CustomerNotFoundException;
import com.example.amazon.mapper.CustomerMapper;
import com.example.amazon.model.Customer;
import com.example.amazon.service.spec.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.amazon.controller.CustomerController.REQUEST_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = REQUEST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "customers available APIs", description = "you can pick one of them")
public class CustomerController {
    public static final String REQUEST_PATH = "/api/customers";
    public static final String GET_REQUEST_PATH = "/{id}";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(value = GET_REQUEST_PATH)
    @Operation(summary = "Get a customer by id", description = "Returns a customer as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad Request - id is not available in the database")
    })
    public CustomerResponseDTO getCustomerById(@PathVariable @Parameter(name = "id", description = "customer id", example = "1") String id) {
        final Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(CustomerMapper.INSTANCE::CustomerToCustomerResponseDTO).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PostMapping
    @Operation(summary = "create a customer", description = "Returns a customer created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - some fields are invalid")
    })
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return new ResponseEntity<>(customerService.save(customerRequestDTO), CREATED);
    }

}
