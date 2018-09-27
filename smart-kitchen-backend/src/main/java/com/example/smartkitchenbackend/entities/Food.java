package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Food{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(mappedBy = "food")
    private Set<NeededIngredient> ingredients;

    @ManyToOne
    private Kitchen kitchen;
}
