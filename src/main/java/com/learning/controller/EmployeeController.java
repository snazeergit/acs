package com.learning.controller;

import com.learning.entity.Employee;
import com.learning.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employees")
@Tag(name = "Employees", description = "Operations related to employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Operation(summary = "Fetch employee by id", description = "Returns a employee based on the provided id")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Employee found"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@Parameter(description = "Employee ID", required = true) @PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> onboardEmployee(@RequestBody Employee employee) {
        String resultMsg = employeeService.onboardEmployee(employee);
        return new ResponseEntity<>(resultMsg, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployeesInPagination(@ParameterObject @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Employee> allEmployees = employeeService.getAllEmployeesInPagination(pageable);
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }
}
