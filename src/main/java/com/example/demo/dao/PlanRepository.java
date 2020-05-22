package com.example.demo.dao;

import com.example.demo.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    void deletePlanById(int id);
}