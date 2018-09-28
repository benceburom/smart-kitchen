package com.example.smartkitchenbackend.repositories.user;

import com.example.smartkitchenbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long> {
}