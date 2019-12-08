package com.springboot.learning.model;

import com.springboot.learning.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    private List<EmployeeEntity> employeeList;

    public List<EmployeeEntity> getEmployeeList() {
        if(employeeList == null) {
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeEntity> employeeList) {
        this.employeeList = employeeList;
    }
}
