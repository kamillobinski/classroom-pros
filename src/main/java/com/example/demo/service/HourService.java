package com.example.demo.service;

import com.example.demo.dao.HourRepository;;
import com.example.demo.entity.Hour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HourService {

    @Autowired
    private HourRepository hourRepository;

    public List<Hour> getHours(){
        return hourRepository.findAll();
    }

}
