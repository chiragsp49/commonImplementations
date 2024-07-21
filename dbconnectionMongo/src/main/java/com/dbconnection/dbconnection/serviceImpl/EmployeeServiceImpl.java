package com.dbconnection.dbconnection.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dbconnection.dbconnection.entity.Employee;
import com.dbconnection.dbconnection.repository.EmployeeRepository;
import com.dbconnection.dbconnection.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployee(UUID id) {
		return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee Found"));
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(String name, String dept) {
		Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.findByName(name));
		if(employeeOptional.isEmpty()) {
			Employee newEmployee = new Employee(UUID.randomUUID(),name,dept);
			return employeeRepository.save(newEmployee);
		}else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already exists");
		}
	}

	@Override
	public Employee updateEmployee(UUID id, String name , String dept) {
		Employee employee  = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee Found"));
		if(employee!=null) {
			employee.setName(name);
			employee.setDept(dept);
			Employee updated  = employeeRepository.save(employee);
			return updated;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist");
		}
	}

	@Override
	public void deleteEmployee(UUID id) {
		Employee employee  = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Employee Found"));
		if(employee!=null) {
			employeeRepository.delete(employee);
		}
		
	}

}
