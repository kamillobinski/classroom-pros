package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class RoomTests {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void Should_Create_Room() {
        String number = "1234";

        Room room = new Room();
        room.setNumber(number);
        roomRepository.save(room);

        Assertions.assertNotNull(roomRepository.findByNumber(number));
    }

    @Test
    public void Should_Delete_Room() {
        String number = "1234";

        Room room = new Room();
        room.setNumber(number);
        roomRepository.save(room);
        roomRepository.delete(roomRepository.findByNumber(number));

        Assertions.assertFalse(roomRepository.existsByNumber(number));
    }

    @Test
    public void Should_Get_All_Rooms() {
        List<Room> rooms = roomRepository.findAll();
        Assertions.assertTrue(rooms.size() > 0);
    }
}
