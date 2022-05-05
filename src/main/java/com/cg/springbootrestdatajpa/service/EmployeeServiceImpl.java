package com.cg.springbootrestdatajpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springbootrestdatajpa.model.Employee;
import com.cg.springbootrestdatajpa.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	@Override
	public Optional<Employee> getEmployeeById(Long empId){
		return employeeRepository.findById(empId);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	@Override
	public List<Employee> getEmployeeByLastName(String lastName) {
		return employeeRepository.findByLastName(lastName);
	}

	@Override
	public List<Employee> getEmployeeByEmailId(String emailId) {
		return employeeRepository.findByEmailId(emailId);
	}
}
