package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.LoginData;

public interface loginRepository extends JpaRepository<LoginData, String>{

}
