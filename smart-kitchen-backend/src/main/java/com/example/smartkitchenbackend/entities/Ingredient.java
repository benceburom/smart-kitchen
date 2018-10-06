package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Ingredient implements Serializable {
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
