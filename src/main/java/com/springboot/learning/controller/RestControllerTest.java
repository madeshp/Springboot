package com.springboot.learning.controller;

import com.springboot.learning.DAO.EmployeeDAO;
import com.springboot.learning.model.Employee;
import com.springboot.learning.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@RestController
@RequestMapping("/employee")
public class RestControllerTest {
    @Autowired
    private EmployeeDAO employeeDAO;
    @GetMapping(path = "/list/all",produces =  "application/json")
    public Employees getEmployeeList() {
return employeeDAO. getAllEmployees();

    }

    @GetMapping(path = "/{id}",produces =  "application/json")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeDAO. getAllEmployees().getEmployeeList().get(id);

    }
}
