package com.example.demo.service;


import com.example.demo.dao.PlanRepository;
import com.example.demo.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> getAllPlans() { return planRepository.findAll(); };

    public Plan getPlanById(int id){
        return planRepository.findById(id).orElse(null);
    }

    public Plan savePlan (Plan plan) { return planRepository.save(plan); }
}
