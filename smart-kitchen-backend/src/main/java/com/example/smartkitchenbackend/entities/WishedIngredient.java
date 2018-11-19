package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WishedIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double weightOrCount;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private WishList wishList;
}
