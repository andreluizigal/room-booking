package com.andreluizigal.roombooking.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ReservationPostRequest(
        @NotNull(message = "User ID cannot be null")
        @Positive(message = "User ID must be a positive number")
        Long userId,
        @NotNull(message = "Room ID cannot be null")
        @Positive(message = "Room ID must be a positive number")
        Long roomId,
        @NotNull(message = "Guests cannot be null")
        @Positive(message = "Guests must be a positive number")
        int guests,
        @NotNull(message = "Start Date cannot be null")
        LocalDate startDate,
        @NotNull(message = "End Date cannot be null")
        LocalDate endDate
) {
}
