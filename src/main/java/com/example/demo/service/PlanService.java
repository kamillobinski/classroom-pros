package com.example.demo.service;


import com.example.demo.dao.PlanRepository;
import com.example.demo.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan getPlanById(int id){
        return planRepository.findById(id).orElse(null);
    }
}
