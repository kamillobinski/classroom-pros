package com.example.demo.dao;

import com.example.demo.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository <Classes, Integer> {
}
