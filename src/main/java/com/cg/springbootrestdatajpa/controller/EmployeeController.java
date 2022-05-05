package com.cg.springbootrestdatajpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springbootrestdatajpa.exception.EmployeeNotFoundException;
import com.cg.springbootrestdatajpa.model.Employee;
import com.cg.springbootrestdatajpa.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(produces="application/json", value="Operations related to Employee")
public class EmployeeController {
	@Autowired
	public EmployeeService employeeService;
	
	@PostMapping("/employee/new")
	@ApiOperation(value="Create a new Employee", response=Employee.class)
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/employee/all")
	@ApiOperation(value="View all Employees", response=Iterable.class)
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/employee/id/{id}")
	@ApiOperation(value="Get employee by id")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empId) throws EmployeeNotFoundException{
		Employee employee =  employeeService.getEmployeeById(empId).orElseThrow(()->new EmployeeNotFoundException("No Employee Found with this id: "+empId));
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("/employee/lastname/{lastname}")
	@ApiOperation(value="Get employee by lastname")
	public List<Employee> getEmployeeByLastName(@PathVariable(value="lastname") String lastName) throws EmployeeNotFoundException{
		return employeeService.getEmployeeByLastName(lastName);
	}
	
	@GetMapping("/employee/emailid/{emailid}")
	@ApiOperation(value="Get employee by email-id")
	public List<Employee> getEmployeeByEmailId(@PathVariable(value="emailid") String emailId) throws EmployeeNotFoundException{
		return employeeService.getEmployeeByEmailId(emailId);
	}
	
	@PutMapping("/employee/update/{id}")
	@ApiOperation(value="Update existing Employee", response=ResponseEntity.class)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empId,@Valid @RequestBody Employee employee) throws EmployeeNotFoundException{
		Employee emp =  employeeService.getEmployeeById(empId).orElseThrow(()->new EmployeeNotFoundException("No Employee Found with this id: "+empId));
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		Employee newDetails = employeeService.updateEmployee(emp);
		return ResponseEntity.ok(newDetails);
	}
	
	@DeleteMapping("/employee/delete/{id}")
	@ApiOperation(value="Delete an Employee")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long empId) throws EmployeeNotFoundException{
		Employee employee =  employeeService.getEmployeeById(empId).orElseThrow(()->new EmployeeNotFoundException("No Employee Found with this id: "+empId));
		employeeService.deleteEmployee(employee);
		Map<String, Boolean> result = new HashMap<>();
		result.put("Deleted", Boolean.TRUE);
		return result;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}
}
