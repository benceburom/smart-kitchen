package com.example.smartkitchenbackend.repositories.kitchen;

import com.example.smartkitchenbackend.entities.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenJPARepository extends JpaRepository<Kitchen, Long> {
}