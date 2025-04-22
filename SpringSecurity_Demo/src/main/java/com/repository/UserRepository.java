package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.model.User;

public interface UserRepository extends JpaRepository<User,String>
{

	 @EntityGraph(attributePaths = "roles")
	Optional<User> findByEmail(String email);

}


