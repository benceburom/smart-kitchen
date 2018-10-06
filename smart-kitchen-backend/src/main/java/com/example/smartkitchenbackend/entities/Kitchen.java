package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Kitchen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToMany
	private List<User> users = new ArrayList<>();

	@OneToMany(mappedBy = "kitchen")
	private Set<IngredientInKitchen> ingredients;

	@OneToMany(mappedBy = "kitchen")
	private Set<WishList> wishLists;

	@OneToMany(mappedBy = "kitchen")
	private Set<Food> foods;

	public void addUser(User user) {
		users.add(user);
		user.addKitchen(this);
	}
}

   

