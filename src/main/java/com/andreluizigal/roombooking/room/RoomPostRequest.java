package com.andreluizigal.roombooking.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoomPostRequest(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotNull(message = "Capacity cannot be null")
        @Positive(message = "Capacity must be a positive number")
        int capacity
) {
}

