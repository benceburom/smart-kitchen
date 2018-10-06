package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.AddKitchenDTO;
import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.services.Converters.UserConverter;
import com.example.smartkitchenbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserEndpoint {
	private final UserService userService;

	@GetMapping("/{userId}")
	public UserDTO getAttendee(@PathVariable("userId") long userId) {
		return UserConverter.toDTO(userService.getUserById(userId));
	}

	@GetMapping("/list")
	public User getUser() {
		return userService.getUsers().stream().findFirst().get();
	}

	@PostMapping("/addKitchen")
	public void addKitchen(@RequestBody AddKitchenDTO addKitchenDTO) {
		userService.addKitchenToUser(addKitchenDTO.getUserId(), addKitchenDTO.getKitchenId());
	}
}
