package com.example.amazon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(String description, @NotNull(message = "email must not be null") @Email String email, String firstName, String lastName) {
}
