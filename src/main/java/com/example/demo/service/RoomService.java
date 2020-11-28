package com.example.demo.service;

import com.example.demo.repository.RoomRepository;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Room room;

    public Room addNewRoom(Room room){
        return roomRepository.save(room);
    }

    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public void deleteRoom(int id){
        roomRepository.deleteById(id);
    }
}