package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "wishList")
    private List<WishedIngredient> ingredients = new ArrayList<>();

    @OneToOne
    private Kitchen kitchen;
}
