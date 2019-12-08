package com.springboot.learning.services;

import com.springboot.learning.entity.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {

    public List<EmployeeEntity> getAllEmployees();

    public List<EmployeeEntity> saveEmployee(List<EmployeeEntity> employeeList);

    public List<EmployeeEntity> findByUserName(String firstName, String lastName);
}
