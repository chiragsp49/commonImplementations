package com.dbconnection.dbconnection.repository;


import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dbconnection.dbconnection.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, UUID>{
	
	public Employee findByName(String name);
}
