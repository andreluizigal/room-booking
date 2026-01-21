package com.andreluizigal.roombooking.reservation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationPostRequest> save(@RequestBody @Valid ReservationPostRequest reservationPostRequest) {
        ReservationPostRequest saved = service.save(reservationPostRequest);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> findAll() {
        List<ReservationResponse> foundList = service.findAll();
        return ResponseEntity.ok(foundList);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity cancel(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

}
