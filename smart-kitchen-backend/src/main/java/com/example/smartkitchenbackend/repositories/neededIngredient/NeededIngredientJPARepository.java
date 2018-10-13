package com.example.smartkitchenbackend.repositories.neededIngredient;

import com.example.smartkitchenbackend.entities.NeededIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeededIngredientJPARepository extends JpaRepository<NeededIngredient, Long> {
}