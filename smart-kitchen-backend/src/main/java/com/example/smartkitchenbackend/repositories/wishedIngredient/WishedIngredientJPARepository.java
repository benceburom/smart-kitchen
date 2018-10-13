package com.example.smartkitchenbackend.repositories.wishedIngredient;

import com.example.smartkitchenbackend.entities.WishedIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishedIngredientJPARepository extends JpaRepository<WishedIngredient, Long> {
}