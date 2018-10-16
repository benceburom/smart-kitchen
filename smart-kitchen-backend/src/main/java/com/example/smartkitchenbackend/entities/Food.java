package com.example.smartkitchenbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Food{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(mappedBy = "food")
	private List<NeededIngredient> ingredients = new ArrayList<>();

    @ManyToOne
    private Kitchen kitchen;
}
