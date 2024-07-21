package com.dbconnection.dbconnection.service;

import java.util.List;
import java.util.UUID;

import com.dbconnection.dbconnection.entity.Employee;

public interface EmployeeService {

	public Employee getEmployee(UUID id);
	
	public List<Employee> getAllEmployee();
	
	public Employee createEmployee(String name, String dept);
	
	public Employee updateEmployee(UUID id, String name, String dept);
	
	public void deleteEmployee(UUID id);
}
