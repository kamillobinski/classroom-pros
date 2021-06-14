package com.example.demo.controller;

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
        roomService.addNewRoom(model, number);
        return "redirect:/lesson-manager";
    }

    @GetMapping("/deleteRoom/{id}")
    public String deleteRoom(@PathVariable(value = "id") int id){
        roomService.deleteRoom(id);
        return "lesson-manager";

    }

}
