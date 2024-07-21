package com.dbconnection.dbconnection.controller;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbconnection.dbconnection.entity.Employee;
import com.dbconnection.dbconnection.pojo.EmployeeRequest;
import com.dbconnection.dbconnection.service.EmployeeService;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee getEmployee(@PathVariable(value = "id")UUID id) {
		return employeeService.getEmployee(id);
	}
	
	@GetMapping(path="/all")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}

	
	@PostMapping(path="",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody(required = true) EmployeeRequest empRequest) {
		return employeeService.createEmployee(empRequest.getName(), empRequest.getDept());
	}
	
	@PutMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Employee updateEmployee(@PathVariable(value="id",required = true)UUID id, @RequestBody(required = true)EmployeeRequest empRequest) {
		return employeeService.updateEmployee(id, empRequest.getName(), empRequest.getDept());
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable(value="id",required = true)UUID id) {
		employeeService.deleteEmployee(id);
	}
}

