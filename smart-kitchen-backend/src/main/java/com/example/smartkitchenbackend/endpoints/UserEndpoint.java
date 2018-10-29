package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.Kitchen.AddKitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.security.CurrentUser;
import com.example.smartkitchenbackend.security.UserPrincipal;
import com.example.smartkitchenbackend.services.Converters.UserConverter;
import com.example.smartkitchenbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserEndpoint {
	private final UserService userService;

	@GetMapping("/{userId}")
	public UserDTO getUser(@PathVariable("userId") long userId) {
		return UserConverter.toDTO(userService.getUserById(userId));
	}

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public UserDTO getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		return new UserDTO(currentUser.getId(), currentUser.getName(), currentUser.getEmail(), currentUser.getUsername());
	}

	@GetMapping("/list")
	public List<UserDTO> getUser() {
		return userService.getUsers();
	}

	@GetMapping("/listKitchens/{userId}")
	public List<KitchenDTO> getKitchensByUserId(@PathVariable("userId") long userId) {
		return userService.getKitchensByUserId(userId);
	}

	@PostMapping("/addKitchen")
	public KitchenDTO addKitchen(@RequestBody AddKitchenDTO addKitchenDTO) {
		return userService.addKitchenToUser(addKitchenDTO.getUserId(), addKitchenDTO.getKitchenId());
	}
}
