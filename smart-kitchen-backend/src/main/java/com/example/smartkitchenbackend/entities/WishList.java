package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class WishList implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy = "wishList")
	private Set<WishedIngredient> ingredients;
	
	@ManyToOne
    private Kitchen kitchen;
}
