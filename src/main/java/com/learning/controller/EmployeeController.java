package com.learning.controller;

import com.learning.entity.Employee;
import com.learning.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/emp")
@Tag(name = "Employees", description = "Operations related to employees")
public class EmployeeController {

    @Autowired
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

    @Operation(summary = "Fetch employee by id", description = "Returns a employee based on the provided id")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Employee found"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployee(@Parameter(description = "Employee ID", required = true) @RequestParam Long id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Operation(summary = "Onboard a new employee", description = "Onboard a new employee with the provided details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee onboarded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data")
    })

    @PostMapping("/onboard")
    public ResponseEntity<String> onboardEmployee(@Parameter(description = "Employee details needed", required = true) @RequestBody Employee employee) {
        String resultMsg = employeeService.onboardEmployee(employee);
        return new ResponseEntity<>(resultMsg, HttpStatus.CREATED);
    }
}
