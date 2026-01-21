package com.andreluizigal.roombooking.room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping
    public ResponseEntity<RoomResponse> save(@RequestBody @Valid RoomPostRequest userPostrequest) {
        RoomResponse saved = service.save(userPostrequest);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<RoomResponse> update(@RequestBody @Valid RoomPutRequest userPutrequest) {
        RoomResponse updated = service.update(userPutrequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> findAll() {
        List<RoomResponse> foundList = service.findAll();
        return ResponseEntity.ok(foundList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable
                                         @NotNull(message = "ID cannot be null")
                                         @Positive(message = "ID must be a positive number")
                                         Long id) {
        RoomResponse found = service.findById(id);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable
                                       @NotNull(message = "ID cannot be null")
                                       @Positive(message = "ID must be a positive number")
                                       Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
