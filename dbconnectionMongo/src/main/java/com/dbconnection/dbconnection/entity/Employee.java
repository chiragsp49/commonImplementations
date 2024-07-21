package com.dbconnection.dbconnection.entity;



import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "employees")
public class Employee {

	private UUID id;
	private String name;
	private String dept;

	
	public Employee() {
		super();
	}


	public Employee(UUID id ,String name, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}
	
	


}
