package com.example.demo.repository;

import com.example.demo.entity.Hour;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HourRepository extends JpaRepository<Hour, Integer> {
}
