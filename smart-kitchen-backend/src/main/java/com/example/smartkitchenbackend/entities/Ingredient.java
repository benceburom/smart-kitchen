package com.example.smartkitchenbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
    
	@OneToMany(mappedBy = "ingredient")
    private Set<WishedIngredient> wishLists;
    
    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientInKitchen> kitchens;
    
    @OneToMany(mappedBy = "ingredient")
    private Set<NeededIngredient> foods;
}
