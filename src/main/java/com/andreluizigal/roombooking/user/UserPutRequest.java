package com.andreluizigal.roombooking.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserPutRequest(

        @NotNull(message = "ID cannot be null")
        @Positive(message = "ID must be a positive number")
        Long id,
        @NotBlank(message = "Name cannot be null or blank")
        String name
) {
}
