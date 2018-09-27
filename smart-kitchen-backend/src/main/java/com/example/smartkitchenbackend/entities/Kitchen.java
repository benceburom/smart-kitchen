package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Kitchen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToMany
	private Set<User> users;

	@OneToMany(mappedBy = "kitchen")
	private Set<IngredientInKitchen> ingredients;

	@OneToMany(mappedBy = "kitchen")
	private Set<WishList> wishlists;

	@OneToMany(mappedBy = "kitchen")
	private Set<Food> foods;
}

   

