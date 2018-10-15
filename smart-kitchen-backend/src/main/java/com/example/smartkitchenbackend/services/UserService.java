package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.UserDTO;
import com.example.smartkitchenbackend.DTOs.payload.ApiResponse;
import com.example.smartkitchenbackend.DTOs.payload.JwtAuthenticationResponse;
import com.example.smartkitchenbackend.DTOs.payload.LoginRequest;
import com.example.smartkitchenbackend.DTOs.payload.SignUpRequest;
import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.security.JwtTokenProvider;
import com.example.smartkitchenbackend.services.Converters.KitchenConverter;
import com.example.smartkitchenbackend.services.Converters.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final KitchenService kitchenService;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;


	public User getUserById(long userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + userId)
		);
	}

	public List<UserDTO> getUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserConverter::toDTO)
				.collect(Collectors.toList());
	}

	public void addKitchenToUser(long userId, long kitchenId) {
		kitchenService.addUserToKitchen(kitchenId, userId);
	}

	public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleService.findByName(RoleName.ROLE_USER);

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	public List<KitchenDTO> getKitchensByUserId(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));
		return user.getKitchens().stream().map(KitchenConverter::toKitchenDTO).collect(Collectors.toList());
	}
}
