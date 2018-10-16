package com.example.smartkitchenbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy = "wishList")
	private Set<WishedIngredient> ingredients;

	@OneToOne
	private Kitchen kitchen;
}
