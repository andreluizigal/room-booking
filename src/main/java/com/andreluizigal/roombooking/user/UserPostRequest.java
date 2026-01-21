package com.andreluizigal.roombooking.user;

import jakarta.validation.constraints.NotBlank;

public record UserPostRequest(
        @NotBlank(message = "Name cannot be empty")
        String name) {
}
