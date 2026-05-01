package com.learning.service;

import com.learning.exception.EmployeeNotFoundException;
import com.learning.entity.Employee;
import com.learning.repository.EmployeeProjection;
import com.learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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

    public Page<Employee> getAllEmployeesInPagination(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
