package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/add-room-action", method = RequestMethod.POST)
    public String addNewRoom(Model model, @RequestParam("add-room-number-input") String number) {

        // Check if input values are not empty
        if (number != "") {

            // Creates new room based on the entered data in the form
            Room newRoom = new Room();
            newRoom.setNumber(number);

            roomService.addNewRoom(newRoom);
        }else {
            if(number == "") {
                model.addAttribute("message", "Could not add room without data.");
            }
        }
        return "redirect:/lesson-manager";
    }

    @GetMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable(value = "id") int id){
        roomService.deleteRoom(id);
        return "lesson-manager";

    }

}
