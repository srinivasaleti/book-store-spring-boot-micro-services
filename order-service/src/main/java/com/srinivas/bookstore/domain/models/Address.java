package com.srinivas.bookstore.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Address(
        @NotBlank(message = "Line1 must not be blank")
        String line1,

        String line2, // Optional, so no validation

        @NotBlank(message = "City must not be blank")
        String city,

        @NotBlank(message = "State must not be blank")
        String state,

        @NotBlank(message = "Zip code must not be blank")
        @Pattern(
                regexp = "^\\d{5}(-\\d{4})?$",
                message = "Zip code must be valid (e.g., 12345 or 12345-6789)"
        )
        String zipCode,

        @NotBlank(message = "Country must not be blank")
        String country
) {}
