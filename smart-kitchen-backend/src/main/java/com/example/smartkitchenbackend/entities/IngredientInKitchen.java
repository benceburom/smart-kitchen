package com.example.smartkitchenbackend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class IngredientInKitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double weightOrCount;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private Kitchen kitchen;
}
