package com.payroll.microservices.employeepayrollservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeePayrollController {
	
	@Autowired
	EmployeePayrollRepository employeePayrollRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/employee/{empId}/role/{roleName}")
	public EmployeePayroll insertEmployeeePayrollDetails(@PathVariable Long empId,@PathVariable String roleName) {
		//EmployeePayroll employeePayroll = new EmployeePayroll(1L,100L,"AAA","BBB",1001L,"HR","HumanResource");
		
		//ResponseEntity<EmployeePayroll> employeeEntity = new RestTemplate().getForEntity("http://localhost:8181/employee/{empId}", EmployeePayroll.class,empId);
		EmployeePayroll employeePayroll = employeeService.getEmployeeDetails(empId);
		
		//ResponseEntity<EmployeePayroll> roleEntity = new RestTemplate().getForEntity("http://localhost:8282/role/{roleName}", EmployeePayroll.class,roleName);
		//EmployeePayroll employeePayroll = employeeEntity.getBody();
		EmployeePayroll roleDetails = roleService.getRoleByRoleName(roleName);
		
		employeePayroll.setRoleId(roleDetails.getRoleId());
		employeePayroll.setRoleName(roleDetails.getRoleName());
		employeePayroll.setDescription(roleDetails.getDescription());
		
		employeePayrollRepository.save(employeePayroll);
		System.out.println("employeePayroll ="+employeePayroll);
		return employeePayroll;
	}
	

}
