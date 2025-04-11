package com.srinivas.bookstore.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Customer(
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone must not be blank")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone must be a 10-digit number"
        )
        String phone
) {}
