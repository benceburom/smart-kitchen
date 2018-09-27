package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class WishedIngredient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private double weightOrCount;
	
	@ManyToOne
    private Ingredient ingredient;
	
	@ManyToOne
    private WishList wishList;
}
