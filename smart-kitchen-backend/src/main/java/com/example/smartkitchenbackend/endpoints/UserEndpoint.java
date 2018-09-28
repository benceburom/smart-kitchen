package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.Dtos.UserDTO;
import com.example.smartkitchenbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {
	private final UserService userService;

	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody UserDTO userDTO) {
		return userService.registerNewUser(userDTO);
	}
}
