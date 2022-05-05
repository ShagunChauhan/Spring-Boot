package com.cg.springbootrestdatajpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.springbootrestdatajpa.model.Employee;
import com.cg.springbootrestdatajpa.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
	@InjectMocks
	private EmployeeServiceImpl service;
	
	@Mock
	private EmployeeRepository repository;
	
	@Test
	public void testCreateEmployee() {
		Employee employee = new Employee(14, "Nikhil", "Tomar", "nt@gmail.com");
		service.createEmployee(employee);
		verify(repository, times(1)).save(employee);
	}
	
	@Test
	public void testGetAllEmployee() {
		List<Employee> empList = new ArrayList<>();
		Employee employee1 = new Employee(14, "Nikhil", "Tomar", "nt@gmail.com");
		Employee employee2 = new Employee(15, "Kunal", "Malik", "mk@gmail.com");
		Employee employee3 = new Employee(16, "Sagar", "Thakur", "ts@gmail.com");
		empList.add(employee1);
		empList.add(employee2);
		empList.add(employee3);
		when(repository.findAll()).thenReturn(empList);
		List<Employee> emp = service.getAllEmployee();
		assertEquals(3, emp.size());
	}
	
	@Test
	public void testGetEmployeeById() {
		List<Employee> empList = new ArrayList<>();
		Employee employee1 = new Employee(14, "Nikhil", "Tomar", "nt@gmail.com");
		Employee employee2 = new Employee(15, "Kunal", "Malik", "mk@gmail.com");
		Employee employee3 = new Employee(16, "Sagar", "Thakur", "ts@gmail.com");
		empList.add(employee1);
		empList.add(employee2);
		empList.add(employee3);
		when(repository.findById(15L)).thenReturn(Optional.of(new Employee(15, "Kunal", "Malik", "mk@gmail.com")));
		Optional<Employee> emp = service.getEmployeeById(15L);
		assertEquals(true, emp.isPresent());
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee(14, "Nikhil", "Tomar", "nt@gmail.com");
		employee.setFirstName("Kashyap");
		service.updateEmployee(employee);
		assertEquals("Kashyap", employee.getFirstName());
	}
	
	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee(14, "Nikhil", "Tomar", "nt@gmail.com");
		service.deleteEmployee(employee);	
		Optional<Employee> emp = service.getEmployeeById(14L);
		assertEquals(false, emp.isPresent());
	}
}
