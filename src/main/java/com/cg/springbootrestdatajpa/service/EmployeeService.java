package com.cg.springbootrestdatajpa.service;

import java.util.List;
import java.util.Optional;

import com.cg.springbootrestdatajpa.model.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
	List<Employee> getAllEmployee();
	Optional<Employee> getEmployeeById(Long empId);
	Employee updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	List<Employee> getEmployeeByLastName(String lastName);
	List<Employee> getEmployeeByEmailId(String emailId);
}
