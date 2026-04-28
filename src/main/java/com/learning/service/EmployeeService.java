package com.learning.service;

import com.learning.exception.EmployeeNotFoundException;
import com.learning.entity.Employee;
import com.learning.repository.EmployeeProjection;
import com.learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public String greetEmployee() {
        return "Welcome to KR Consulting Services";
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public String onboardEmployee(Employee employee) {
        Optional<Employee> existing = employeeRepository.findByName(employee.getName());
        //Optional<Employee> existing = employeeRepository.findByNameNative(employee.getName());
        //Optional<EmployeeProjection> existing = employeeRepository.findProjectionByName(employee.getName());
        if (existing.isPresent()) {
            return employee.getName() + " already onboarded";
        }
        Employee saved = employeeRepository.save(employee);
        return saved.getName() + " onboarded successfully";
    }
}
