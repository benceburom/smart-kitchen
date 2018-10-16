package com.example.smartkitchenbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

	@OneToOne
	private WishList wishList;

	@OneToMany(mappedBy = "kitchen")
	private Set<Food> foods;

	public void addUser(User user) {
		users.add(user);
		user.addKitchen(this);
	}
}

   

