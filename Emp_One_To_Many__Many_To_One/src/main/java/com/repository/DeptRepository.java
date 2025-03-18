package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Department;

public interface DeptRepository extends JpaRepository<Department,Long> 
{
//	@Query("Select d from Department d where d.deptName =:name")
//	public Department getByName(String name);
	
	
	@Query("SELECT d FROM Department d WHERE d.deptName = :name")
	Department getByName(@Param("name") String name);

	
}

