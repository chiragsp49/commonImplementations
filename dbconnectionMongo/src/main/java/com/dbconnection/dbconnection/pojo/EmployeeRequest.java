package com.dbconnection.dbconnection.pojo;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "employee")
public class EmployeeRequest {
	private String name;
	private String dept;
	
	
	public EmployeeRequest(String name, String dept) {
		super();
		this.name = name;
		this.dept = dept;
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
