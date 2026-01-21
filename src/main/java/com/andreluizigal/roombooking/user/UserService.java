package com.andreluizigal.roombooking.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse save(@Valid UserPostRequest postRequest) {
        User user = new User();
        user.setName(postRequest.name());
        return toResponse(repository.save(user));
    }

    public UserResponse update(@Valid UserPutRequest putrequest) {
        User user = repository.findById(putrequest.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("User with ID '%d' not found", putrequest.id())));
        user.setName(putrequest.name());
        return toResponse(repository.save(user));
    }

    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                            String.format("User with ID '%d' not found", id)));
        return  toResponse(user);
    }

    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("User with ID '%d' not found", id)));
        repository.delete(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName()
        );
    }
}
