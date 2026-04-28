package com.learning.controller;

import com.learning.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/emp")
@Tag(name = "Employees", description = "Operations related to employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(summary = "Welcome message", description = "Returns welcome message for employees")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Welcome"),
                    @ApiResponse(responseCode = "404", description = "Something went wrong")
            }
    )
    @GetMapping("/welcome")
    public String greetEmployee() {
        return employeeService.greetEmployee();
    }
}
