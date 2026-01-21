package com.andreluizigal.roombooking.reservation;

import java.time.LocalDate;

public record ReservationResponse(
        Long id,
        Long userId,
        String userName,
        Long roomId,
        String roomName,
        int guests,
        LocalDate startDate,
        LocalDate endDate,
        ReservationStatus status
) {
}
