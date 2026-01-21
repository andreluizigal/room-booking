package com.andreluizigal.roombooking.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserPostRequest userPostrequest) {
        UserResponse saved = service.save(userPostrequest);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserPutRequest userPutrequest) {
        UserResponse updated = service.update(userPutrequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> foundList = service.findAll();
        return ResponseEntity.ok(foundList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable
                                         @NotNull(message = "ID cannot be null")
                                         @Positive(message = "ID must be a positive number")
                                         Long id) {
        UserResponse found = service.findById(id);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<User> delete(@PathVariable
                                       @NotNull(message = "ID cannot be null")
                                       @Positive(message = "ID must be a positive number")
                                       Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}