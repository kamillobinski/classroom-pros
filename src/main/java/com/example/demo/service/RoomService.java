package com.example.demo.service;

import com.example.demo.repository.RoomRepository;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room addNewRoom(Room room){
        return roomRepository.save(room);
    }
}