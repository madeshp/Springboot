package com.springboot.learning.controller;

import com.google.common.io.Resources;
import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.model.EmployeeModel;
import com.springboot.learning.repository.IEmployeeRepository;
import com.springboot.learning.services.EmployeeServiceImpl;
import com.springboot.learning.services.IEmployeeService;
import com.springboot.learning.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {EmployeeRestController.class})
class EmployeeRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JsonUtils jsonUtils;
 @MockBean
    EmployeeServiceImpl employeeServiceImpl;
   EmployeeRestController employeeRestController;
    List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
    List<EmployeeEntity> findByFirstAndLastName = new ArrayList<EmployeeEntity>();
    List<EmployeeModel> employeeModelList = new ArrayList<EmployeeModel>();
    @Autowired
    ResourceLoader resourceLoader;

    @BeforeEach
    void setUp() {
        employeeRestController=new EmployeeRestController(employeeServiceImpl,jsonUtils);
        initMocks(this);
        employeeEntityList.add(EmployeeEntity.builder().uuid("test").lastName("test").firstName("test").email("test").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test1").lastName("test1").firstName("test1").email("test1").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test2").lastName("test2").firstName("test2").email("test2").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test3").lastName("test3").firstName("test3").email("test3").build());
        // For getbyall employee operation
        Mockito.when(employeeServiceImpl.getAllEmployees()).thenReturn(employeeEntityList);
        findByFirstAndLastName.add(EmployeeEntity.builder().id(12345).uuid("12345test").lastName("12345test").firstName("12345test").email("12345test").build());
        // For findby first and last name  operation
        Mockito.when(employeeServiceImpl.findByUserName("12345test", "12345test")).thenReturn(findByFirstAndLastName);
        // For Save operation

    }
    @Test
    public void contextLoads() {
        Assertions.assertNotNull(employeeRestController);
    }

    @Test
    @DisplayName("GET /getAllEmployees should return all employess")
    @SneakyThrows
    public void getAllEmployeeTest() throws Exception {
        String apiUrl = "/getAllEmployees";
        // Setup "Mockito" to mock userService call
        // Build a GET Request and send it to the test server
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult response = mockMvc.perform(rb).andReturn(); // throws Exception
        System.out.println(response.getResponse());
        // Validate response
        String responseAsString = response.getResponse().getContentAsString();
        System.out.println("API Response String as JSON:" + responseAsString);
        System.out.println("Mock Response String as JSON" + getExpectedJSONResponse());
        JSONAssert.assertEquals(response.getResponse().getStatus() + "", "" + HttpStatus.OK.value(), true);
        JSONAssert.assertEquals(getExpectedJSONResponse(), responseAsString, true);

    }

    @Test
    @DisplayName("GET /getAllEmployees should return all employess")
    @SneakyThrows
    public void getByUserNameControllerTest() throws Exception {
        String apiUrl = "/getAllEmployees";
        // Setup "Mockito" to mock userService call
        // Build a GET Request and send it to the test server
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult response = mockMvc.perform(rb).andReturn(); // throws Exception
        System.out.println(response.getResponse());
        // Validate response
        String responseAsString = response.getResponse().getContentAsString();
        System.out.println("API Response String as JSON:" + responseAsString);
        System.out.println("Mock Response String as JSON" + getExpectedJSONResponse());
        JSONAssert.assertEquals(response.getResponse().getStatus() + "", "" + HttpStatus.OK.value(), true);
        JSONAssert.assertEquals(getExpectedJSONResponse(), responseAsString, true);
 }

	@Test
	@DisplayName("addEmployee to database")
	 public void addEmployeeTest() throws Exception {
        String apiUrl = "/addEmployee";
        List<EmployeeEntity> employeeEntityList=new ArrayList<EmployeeEntity>();
        employeeModelList.add(EmployeeModel.builder().id(1).uuid("test").lastName("test").firstName("test").email("test").build());
        employeeModelList.add(EmployeeModel.builder().id(2).uuid("test1").lastName("test1").firstName("test1").email("test1").build());

        List<EmployeeModel> employeeModelList=new ArrayList<EmployeeModel>();
        employeeModelList.add(EmployeeModel.builder().id(1).uuid("test").lastName("test").firstName("test").email("test").build());
        employeeModelList.add(EmployeeModel.builder().id(2).uuid("test1").lastName("test1").firstName("test1").email("test1").build());


        Mockito.when(employeeServiceImpl.saveEmployee(employeeEntityList)).thenReturn(employeeEntityList);
        // Setup "Mockito" to mock userService call
        // Build a GET Request and send it to the test server
        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl).content(new JsonUtils().writeAsJson(employeeEntityList)).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult response = mockMvc.perform(rb).andExpect(status().is2xxSuccessful()).andReturn(); // throws Exception
      /*  System.out.println(response.getResponse());
        // Validate response
        String responseAsString = response.getResponse().getContentAsString();
        System.out.println("API Response String as JSON:" + responseAsString);
        System.out.println("Mock Response String as JSON" + getExpectedJSONResponse());
        JSONAssert.assertEquals(response.getResponse().getStatus() + "", "" + HttpStatus.OK.value(), true);
        JSONAssert.assertEquals(getExpectedJSONResponse(), responseAsString, true);
*/
	 }

    public String getExpectedJSONResponse() throws Exception {
        String expectedResponse = FileUtils.readFileToString(new File(Resources.getResource("response/EmployeeList.json")
                .toURI()), "UTF-8");
     /*   System.out.println("File content data:" + expectedResponse);*/
        return expectedResponse;
    }
}
