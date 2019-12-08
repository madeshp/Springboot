package com.springboot.learning.mapper;

import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.model.EmployeeModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeEntityToModelMapper {
    public static List<EmployeeModel> mapGetAllEmployeeQueryResultToModel(List<EmployeeEntity> employee){
        List<EmployeeModel> employeeModelModelList =new ArrayList<EmployeeModel>();
        employee.forEach(employee1 -> employeeModelModelList.add(EmployeeModel.builder().id(employee1.getId()).firstName(employee1.getFirstName()).lastName(employee1.getLastName()).email(employee1.getEmail()).uuid(employee1.getUuid()).build()));
        return employeeModelModelList;

    }

}
