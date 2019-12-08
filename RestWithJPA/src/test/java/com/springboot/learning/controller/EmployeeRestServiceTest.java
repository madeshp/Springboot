package com.springboot.learning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.model.EmployeeModel;
import com.springboot.learning.repository.IEmployeeRepository;
import com.springboot.learning.services.EmployeeServiceImpl;
import com.springboot.learning.services.IEmployeeService;
import com.springboot.learning.utils.JsonUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {EmployeeRestController.class})
class EmployeeRestServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonUtils jsonUtils;
    @MockBean
    private IEmployeeRepository iEmployeeRepository;

    private IEmployeeService iEmployeeService;
    private List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
    private List<EmployeeEntity> findByFirstAndLastName = new ArrayList<EmployeeEntity>();
    private List<EmployeeModel> employeeModelList = new ArrayList<EmployeeModel>();
    @Autowired
    ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        initMocks(this);
        iEmployeeService = new EmployeeServiceImpl(iEmployeeRepository);
        employeeEntityList.add(EmployeeEntity.builder().uuid("test").lastName("test").firstName("test").email("test").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test1").lastName("test1").firstName("test1").email("test1").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test2").lastName("test2").firstName("test2").email("test2").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test3").lastName("test3").firstName("test3").email("test3").build());
        // For getbyall employee operation

        // For findby first and last name  operation
        findByFirstAndLastName.add(EmployeeEntity.builder().uuid("test").lastName("test").firstName("test").email("test").build());

        Mockito.when(iEmployeeRepository.findByFirstNameAndLastName("test", "test")).thenReturn(findByFirstAndLastName);
        // For Save operation

    }

    @Test
    @DisplayName("GET /getAllEmployees should return all employess")
    @SneakyThrows
    public void getAllEmployeeServiceTest() throws Exception {
        Mockito.when(iEmployeeRepository.findAll()).thenReturn(employeeEntityList);
        List<EmployeeEntity> employeeEntityListRsp = iEmployeeService.getAllEmployees();
        // throws Exception
        System.out.println(employeeEntityListRsp);
        Assertions.assertEquals(employeeEntityListRsp.get(0).getFirstName(), "test");
        Assertions.assertEquals(employeeEntityListRsp.get(1).getLastName(), "test1");

    }

    @Test
    @DisplayName("GET /getAllEmployees should return all employess")
    @SneakyThrows
    public void getByUserNameServiceTest() throws Exception {
        List<EmployeeEntity> employeeEntityListRsp = iEmployeeService.findByUserName("test", "test");
        // throws Exception
        String serviceResponse = new ObjectMapper().writeValueAsString(employeeEntityListRsp);
        System.out.println(employeeEntityListRsp);
        Assertions.assertEquals(employeeEntityListRsp.get(0).getFirstName(), "test");
        Assertions.assertEquals(employeeEntityListRsp.get(0).getLastName(), "test");
    }

    @Test
    @DisplayName("addEmployee to database")
    public void addEmployeeServiceTest() throws Exception {
        List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
        employeeEntityList.add(EmployeeEntity.builder().uuid("test").lastName("test").firstName("test").email("test").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test1").lastName("test1").firstName("test1").email("test1").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test2").lastName("test2").firstName("test2").email("test2").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test3").lastName("test3").firstName("test3").email("test3").build());

        List<EmployeeEntity> saveEmployeeEntityListRsp = new ArrayList<EmployeeEntity>();

        saveEmployeeEntityListRsp.add(EmployeeEntity.builder().id(1).uuid("test").lastName("test").firstName("test").email("test").build());
        saveEmployeeEntityListRsp.add(EmployeeEntity.builder().id(2).uuid("test1").lastName("test1").firstName("test1").email("test1").build());
        saveEmployeeEntityListRsp.add(EmployeeEntity.builder().id(3).uuid("test2").lastName("test2").firstName("test2").email("test2").build());
        saveEmployeeEntityListRsp.add(EmployeeEntity.builder().id(4).uuid("test3").lastName("test3").firstName("test3").email("test3").build());

        Mockito.when(iEmployeeRepository.saveAll(employeeEntityList)).thenReturn(saveEmployeeEntityListRsp);


        // For getbyall employee operation
        List<EmployeeEntity> employeeEntityRsp = iEmployeeService.saveEmployee(employeeEntityList);
        // throws Exception
        String serviceResponse = new ObjectMapper().writeValueAsString(employeeEntityList);
        System.out.println(employeeEntityList);
        Assertions.assertTrue(employeeEntityRsp.get(0).getId() == 1);
        Assertions.assertEquals(employeeEntityRsp.get(0).getFirstName(), "test");
        Assertions.assertEquals(employeeEntityRsp.get(0).getLastName(), "test");

    }

    public String getExpectedJSONResponse() throws Exception {
        String expectedResponse = FileUtils.readFileToString(new File(Resources.getResource("response/EmployeeList.json")
                .toURI()), "UTF-8");
        /*   System.out.println("File content data:" + expectedResponse);*/
        return expectedResponse;
    }
}
