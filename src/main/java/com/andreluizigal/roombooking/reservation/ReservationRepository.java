package com.andreluizigal.roombooking.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
    SELECT r FROM Reservation r
    WHERE r.room.id = :roomId
      AND r.status = :status
      AND :start <= r.endDate
      AND :end >= r.startDate
    """)
    List<Reservation> findConflicts(
            @Param("roomId") Long roomId,
            @Param("status") ReservationStatus status,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}
