package com.andreluizigal.roombooking.User;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "name cannot be empty")
        String name) {
}
