package com.andreluizigal.roombooking.room;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public RoomResponse save(@Valid RoomPostRequest postRequest) {
        Room room = new Room();
        room.setName(postRequest.name());
        room.setCapacity(postRequest.capacity());
        return toResponse(repository.save(room));
    }

    public RoomResponse update(@Valid RoomPutRequest putrequest) {
        Room room = repository.findById(putrequest.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Room with ID '%d' not found", putrequest.id())));
        room.setName(putrequest.name());
        room.setCapacity(putrequest.capacity());
        return toResponse(repository.save(room));
    }

    public List<RoomResponse> findAll() {
        List<Room> rooms = repository.findAll();
        return rooms.stream().map(this::toResponse).toList();
    }

    public RoomResponse findById(Long id) {
        Room room = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Room with ID '%d' not found", id)));
        return toResponse(room);

    }

    public void delete(Long id) {
        Room room = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Room with ID '%d' not found", id)));
        repository.delete(room);
    }

    private RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getCapacity()
        );
    }
}
