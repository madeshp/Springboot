package com.springboot.learning.DAO;

import com.springboot.learning.model.Employee;
import com.springboot.learning.model.Employees;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
    private static Employees list = new Employees();
    static{
        list.getEmployeeList().add(new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));

    }

    public  Employees getAllEmployees() {
        return list;
    }

    public  void addEmployee(Employee empl) {
        list.getEmployeeList().add(empl);
    }
}
