package com.springboot.learning.repository;

import com.springboot.learning.entity.EmployeeEntity;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJdbcRepositories
    public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
     public List<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);

}
