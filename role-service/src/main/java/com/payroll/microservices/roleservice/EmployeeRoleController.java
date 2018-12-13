package com.payroll.microservices.roleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRoleController {
	
	@Autowired
	EmployeeRoleRepository employeeRoleRepository;
	
	@GetMapping("/role/{roleName}")
    public EmployeeRole getRoleByRoleName(@PathVariable String roleName) {
		//return new EmployeeRole(101L,"HR","Human Resources");
		EmployeeRole empRole = employeeRoleRepository.findByRoleName(roleName);
		return empRole;
	}
    
}
