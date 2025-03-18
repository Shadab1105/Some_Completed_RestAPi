package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Emp;

public interface EmpRespository extends JpaRepository<Emp, Long> 
{

}
