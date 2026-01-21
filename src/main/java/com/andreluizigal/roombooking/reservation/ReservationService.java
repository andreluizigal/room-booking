package com.andreluizigal.roombooking.reservation;

import com.andreluizigal.roombooking.room.Room;
import com.andreluizigal.roombooking.room.RoomRepository;
import com.andreluizigal.roombooking.user.User;
import com.andreluizigal.roombooking.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    public ReservationPostRequest save(@Valid ReservationPostRequest postRequest) {
        User user = userRepository.findById(postRequest.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with ID '%d' not found", postRequest.userId())));

        Room room = roomRepository.findById(postRequest.roomId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Room with ID '%d' not found", postRequest.roomId())));


        if(postRequest.guests() > room.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Number of guests higher than the room capacity");
        } else if (postRequest.startDate().isAfter(postRequest.endDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date can not be after end date");
        } else if (!repository.findConflicts(postRequest.roomId(), ReservationStatus.CONFIRMED, postRequest.startDate(), postRequest.endDate()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This room already has a reservation for this period");
        } else {
            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setRoom(room);
            reservation.setGuests(postRequest.guests());
            reservation.setStartDate(postRequest.startDate());
            reservation.setEndDate(postRequest.endDate());
            reservation.setStatus(ReservationStatus.CONFIRMED);

            repository.save(reservation);
            return postRequest;
        }
    }

    public List<ReservationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void cancel(Long id) {
        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Reservation with ID '%d' not found", id)));
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This reservation has already been cancelled");
        } else {
            reservation.setStatus(ReservationStatus.CANCELLED);
            repository.save(reservation);
        }
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getUser().getName(),
                reservation.getRoom().getId(),
                reservation.getRoom().getName(),
                reservation.getGuests(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus()
        );
    }
}
