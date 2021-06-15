package com.example.demo.repository;

import com.example.demo.entity.Plan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class PlanTests {

    @Autowired
    private PlanRepository planRepository;

    @Test
    public void Should_Create_Plan() {
        String name = "testPlan";

        Plan plan = new Plan();
        plan.setName(name);
        planRepository.save(plan);

        Assertions.assertTrue(planRepository.existsByName(name));
    }

    @Test
    public void Should_Find_All_Plans() {
        Should_Create_Plan();
        List<Plan> plans = planRepository.findAll();
        Assertions.assertTrue(plans.size() > 0);
    }

    @Test
    @Rollback(false)
    public void Should_Delete_By_Id() {
        Should_Create_Plan();
        Plan plan = planRepository.findByName("testPlan");
        int planId = plan.getId();

        planRepository.deletePlanById(planId);

        Assertions.assertNull(planRepository.findById(planId));
    }



}
