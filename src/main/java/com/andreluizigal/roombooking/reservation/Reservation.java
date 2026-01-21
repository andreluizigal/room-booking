package com.andreluizigal.roombooking.reservation;

import com.andreluizigal.roombooking.room.Room;
import com.andreluizigal.roombooking.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int guests;

    private LocalDate startDate;
    private LocalDate endDate;

    private ReservationStatus status;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Room room;
}
