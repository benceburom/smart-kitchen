package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.Dtos.UserDTO;
import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.services.Converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public UserDTO registerNewUser(UserDTO newUserDTO) {
		User newUser = UserConverter.toEntity(newUserDTO);
		return UserConverter.toDTO(userRepository.save(newUser));
	}
}
