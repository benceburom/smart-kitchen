package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "kitchen")
    private List<IngredientInKitchen> ingredients = new ArrayList<>();

    @OneToOne
    private WishList wishList;

    @OneToMany(mappedBy = "kitchen")
    private List<Food> foods = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.addKitchen(this);
    }
}

   

