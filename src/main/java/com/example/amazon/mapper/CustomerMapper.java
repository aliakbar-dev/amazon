package com.example.amazon.mapper;

import com.example.amazon.dto.CustomerRequestDTO;
import com.example.amazon.dto.CustomerResponseDTO;
import com.example.amazon.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName")
    })
    Customer CustomerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName")
    })
    CustomerResponseDTO CustomerToCustomerResponseDTO(Customer customer);

}
