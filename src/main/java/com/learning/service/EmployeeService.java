package com.learning.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public String greetEmployee() {
        return "<h1>Welcome to Spring Boot Application</h1>";
    }
}
