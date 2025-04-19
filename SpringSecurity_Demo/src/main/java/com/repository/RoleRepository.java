package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

	Optional<Role> findById(long l);

	Role findByName(String string);

//	Optional<Role> findById(String l);

}
