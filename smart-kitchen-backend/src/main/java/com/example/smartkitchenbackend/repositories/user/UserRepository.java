package com.example.smartkitchenbackend.repositories.user;

import com.example.smartkitchenbackend.entities.User;

import java.util.List;

public interface UserRepository {
	User findByUsernameOrEmail(String username, String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	User findById(long userId);

	List<User> findAll();

	void save(User user);

	User getReference(long userId);
}