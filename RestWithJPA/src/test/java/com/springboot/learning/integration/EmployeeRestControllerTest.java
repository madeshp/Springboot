package com.springboot.learning.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.learning.RestWithJPAApplication;
import com.springboot.learning.controller.EmployeeRestController;
import com.springboot.learning.controller.UnitTestController;
import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.repository.IEmployeeRepository;
import com.springboot.learning.utils.JsonUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
//this will start the server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = RestWithJPAApplication.class)
//MockMvc  this will not start the server but will start the container and can be tested mockmvc
@AutoConfigureMockMvc(print = MockMvcPrint.DEFAULT )
class EmployeeRestControllerTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
	private MockMvc mockMvc;
    // @Autowired
    // private TestRestTemplate restTemplate ;
     @Autowired
     private  EmployeeRestController employeeRestController;

     @Autowired
     private  IEmployeeRepository iEmployeeRepository;
   /* @MockBean
    IEmployeeRepository iEmployeeRepository;*/
/*    HttpHeaders headers = new HttpHeaders();*/
//@Value("${local.server.port}")
  //  private int port;

	/*@BeforeEach*/
	/*void setUp() {
		initMocks(this);
		unitTestController = new UnitTestController();
	}*/
    /*@Test
    public void contextLoads() {
    }*/
    @Test
    public void addEmployeeIntegrationTest(){
        List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
        employeeEntityList.add(EmployeeEntity.builder().uuid("test").lastName("test").firstName("test").email("test").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test1").lastName("test1").firstName("test1").email("test1").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test2").lastName("test2").firstName("test2").email("test2").build());
        employeeEntityList.add(EmployeeEntity.builder().uuid("test3").lastName("test3").firstName("test3").email("test3").build());
        List<EmployeeEntity>  employeeEntityDb= iEmployeeRepository.saveAll(employeeEntityList);
       Assertions.assertEquals(employeeEntityList.get(0).getFirstName(),employeeEntityDb.get(0).getFirstName());
    }
    @Test
    public void checkForNotNullController() throws Exception {
        assertThat(employeeRestController).isNotNull();
    }
    @Test
    public void getAllEmployees() throws Exception {
        this.mockMvc.perform(get("/getAllEmployees")).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(notNullValue()));
        /*containsString("Hello World")
        Object employeeEntityList= this.restTemplate.getForObject("http://localhost:" + port + "/getAllEmployees",
                Object.class,MediaType.APPLICATION_JSON_VALUE);
        List<EmployeeEntity> employeeEntities=(ArrayList<EmployeeEntity>)employeeEntityList;
        assertThat(employeeEntityList).contains("Hello World");

        RequestBuilder rb = MockMvcRequestBuilders.get("/api/getAllEmployees").accept(MediaType.APPLICATION_JSON);
        MvcResult response = mockMvc.perform(rb).andReturn(); // throws Exception
        System.out.println(response.getResponse());
        // Validate response
        String responseAsString = response.getResponse().getContentAsString();
        System.out.println(responseAsString);*/
      //  assertEquals(HttpStatus.OK, response.getStatusCode());
//        JSONAssert.assertEquals(expected, response.getBody(), false);
/*
        verify(mockRepository, times(1)).findAll();*/


       /*Using testRestTemplate
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<EmployeeEntity> response = restTemplate.exchange(
                createURLWithPort("/api/getAllEmployees"),
                HttpMethod.GET, entity, EmployeeEntity.class);

        JSONAssert.assertEquals(getJSONResponseString(), new JsonUtils().toJson(response.getBody()), false);
        */

    }


    @AfterEach
    void deleteRepository() {

       // System.out.println("Test rest server port:"+port);
    }
    private String createURLWithPort(String uri) {

        return "http://localhost:";// + port + uri;
    }


	public String getJSONResponseString() {
		return "{\"totalSize\":1,\"done\":true,\"records\":[{\"attributes\":{\"type\":\"Special_Handling__c\",\"url\":\"/services/data/v43.0/sobjects/Special_Handling__c/a0J0j000000QClCEAW\"},\"LastModifiedDate\":\"2019-02-19T19:24:00.000+0000\",\"Active__c\":true}]}";
	}



}
