package com.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/emp")
public class EmployeeController {

    @RequestMapping("/welcome")
    public String getEmployee() {
        return "<h1>Welcome to Spring Boot Application</h1>";
    }
}
