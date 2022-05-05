package com.cg.springbootrestdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.springbootrestdatajpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByLastName(String lastName);
	List<Employee> findByEmailId(String emailId);
}
