package com.example.smartkitchenbackend.repositories.user;

import com.example.smartkitchenbackend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultUserRepository implements UserRepository {
	private final UserJPARepository userJPARepository;

	@Override
	public User findById(long id) {
		return userJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public User save(User user) {
		return userJPARepository.save(user);
	}

	@Override
	public void delete(User user) {
		userJPARepository.delete(user);
	}
}
