package com.example.smartkitchenbackend.repositories.user;

import com.example.smartkitchenbackend.entities.User;

public interface UserRepository {
	User findById(long id);

	User save(User user);

	void delete(User user);
}
