package com.payroll.microservices.employeeservice;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	//@Autowired
	//Environment environment;
	
	@GetMapping("/employee/{empId}")
	public Optional<Employee> getEmployeeDetails(@PathVariable Long empId) {
		//return new Employee("Sriharsha","Mohan",145412L,new Date());
		Optional<Employee> optEmployee = employeeRepository.findById(empId);
		Employee employee = optEmployee.get();
		//employee.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		
		return optEmployee;
	}

	@GetMapping("/employee/{empId}/fault-tolerance")
	@HystrixCommand(fallbackMethod="fallBackEmployeeDetails")
	public Optional<Employee> getEmployeeDetailsFaultTolerance(@PathVariable Long empId) {
	   throw new RuntimeException("Employee error");
	}
	
	public Employee fallBackEmployeeDetails(@PathVariable Long empId){
		
		return new Employee("Sriharsha","Mohan",145412L,new Date());
	}
	
}
