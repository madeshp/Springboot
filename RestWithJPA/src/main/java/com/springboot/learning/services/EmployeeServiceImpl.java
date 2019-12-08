package com.springboot.learning.services;

import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployeeServiceImpl implements IEmployeeService {

   private  IEmployeeRepository iEmployeeRepository;



    public EmployeeServiceImpl( IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }
    @Override
    public List<EmployeeEntity> getAllEmployees()
    {
        return iEmployeeRepository.findAll();
    }
    @Override
    public List<EmployeeEntity> saveEmployee(List<EmployeeEntity> employeeList) {

        return iEmployeeRepository.saveAll(employeeList);

    }
    @Override
    public List<EmployeeEntity> findByUserName(String firstName, String lastName) {

        return iEmployeeRepository.findByFirstNameAndLastName(firstName, lastName);

    }

}
