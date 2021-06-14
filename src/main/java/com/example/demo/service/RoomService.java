package com.example.demo.service;

import com.example.demo.repository.RoomRepository;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Model addNewRoom(Model model, String number){
        // Check if input values are not empty
        if (!number.equals("")) {

            // Creates new room based on the entered data in the form
            Room newRoom = new Room();
            newRoom.setNumber(number);

            roomRepository.save(newRoom);
        }else {
            if(number.equals("")) {
                model.addAttribute("message", "Could not add room without data.");
            }
        }
        return model;
    }

    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public void deleteRoom(int id){
        roomRepository.deleteById(id);
    }
}