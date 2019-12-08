package com.springboot.learning.DataRest;

import com.springboot.learning.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
/*api/explorer/index.html# to HAL repository*/
import java.util.List;
@RepositoryRestResource(exported = true /*collectionResourceRel = "employee", path = "employee"*/)
public interface IEmployeeRepositoryRestResource extends JpaRepository<EmployeeEntity, Integer> {

        List<EmployeeEntity> findById(@Param("id") String id);
    @RestResource(path = "firstNameStartsWith", rel = "firstNameStartsWith")
    List<EmployeeEntity> findByFirstNameStartsWith(@Param("firstName") String firstName);
}
