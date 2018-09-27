package com.example.smartkitchenbackend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;

@Entity
@Data
public class User{
		   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	
	private String email;

	private String password;

	@ManyToMany
    private Set<Kitchen> kitchens;
	

    
        
   
}
