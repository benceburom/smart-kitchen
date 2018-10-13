package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

	public static User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUserName());

		return user;
	}

	public static UserDTO toDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.userName(user.getUsername())
				.build();
	}
}
