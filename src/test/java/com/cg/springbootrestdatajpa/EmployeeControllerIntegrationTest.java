package com.cg.springbootrestdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.cg.springbootrestdatajpa.model.Employee;

@SpringBootTest(classes=SpringBootRestDatajpaApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port;
	}
	
	@Test
	public void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("William");
		employee.setLastName("Smith");
		employee.setEmailId("swill@gmail.com");
		ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl()+"/api/employee/new", employee, Employee.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testGetAllEmployee() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/api/employee/all", HttpMethod.GET, entity, String.class);
		assertNotNull(getResponse.getBody());
	}
	
	@Test
	public void testGetEmployeeById() {
		long id = 14;
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employee/id/"+id, Employee.class);
		assertEquals(id, employee.getEmpId());
	}
	
	@Test
	public void testGetEmployeeByLastName() {
		String lastName = "Smith";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/api/employee/lastname/"+lastName, HttpMethod.GET, entity, String.class);
		System.out.println(getResponse.getStatusCodeValue());
		assertNotNull(getResponse.getBody());
	}
	
	@Test
	public void testGetEmployeeByEmailId() {
		String emailId = "sk@gmail.com";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/api/employee/emailid/"+emailId, HttpMethod.GET, entity, String.class);
		assertNotNull(getResponse.getBody());
	}
	
	@Test
	public void testUpdateEmployee() {
		long id = 14;
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employee/id/"+id, Employee.class);
		employee.setFirstName("Kashyap");
		employee.setLastName("Singh");
		employee.setEmailId("sk@gmail.com");
		restTemplate.put(getRootUrl()+"/api/employee/update/"+id, employee);
		Employee updatedEmployee = restTemplate.getForObject(getRootUrl()+"/api/employee/id/"+id, Employee.class);
		assertNotNull(updatedEmployee);
		assertEquals(employee.getFirstName(), updatedEmployee.getFirstName());
	}
	
	@Test
	public void testDeleteEmployee() {
		long id = 14;
		Employee employee = restTemplate.getForObject(getRootUrl()+"/api/employee/id/"+id, Employee.class);
		assertEquals(id, employee.getEmpId());
		restTemplate.delete(getRootUrl()+"/api/employee/delete/"+id, employee);
		Employee deleteEmployee = restTemplate.getForObject(getRootUrl()+"/api/employee/id/"+id, Employee.class);		
		assertNotEquals(id, deleteEmployee.getEmpId());
	}
}
