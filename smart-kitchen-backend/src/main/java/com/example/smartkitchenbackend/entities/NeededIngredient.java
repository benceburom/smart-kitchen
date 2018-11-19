package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NeededIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double weightOrCount;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private Food food;


}
