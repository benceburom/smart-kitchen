package com.example.smartkitchenbackend.repositories.role;

import com.example.smartkitchenbackend.entities.Role;
import com.example.smartkitchenbackend.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}