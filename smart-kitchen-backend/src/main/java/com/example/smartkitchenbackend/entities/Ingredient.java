package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String type;
    
	@OneToMany(mappedBy = "ingredient")
    private List<WishedIngredient> wishLists = new ArrayList<>();
    
    @OneToMany(mappedBy = "ingredient")
    private List<IngredientInKitchen> kitchens = new ArrayList<>();
    
    @OneToMany(mappedBy = "ingredient")
    private List<NeededIngredient> foods = new ArrayList<>();
}
