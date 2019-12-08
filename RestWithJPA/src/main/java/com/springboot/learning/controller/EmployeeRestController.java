package com.springboot.learning.controller;

import com.springboot.learning.entity.EmployeeEntity;
import com.springboot.learning.exception.EmployeeNotFoundException;
import com.springboot.learning.mapper.EmployeeEntityToModelMapper;
import com.springboot.learning.model.EmployeeList;
import com.springboot.learning.model.EmployeeModel;
import com.springboot.learning.services.EmployeeServiceImpl;
import com.springboot.learning.services.IEmployeeService;
import com.springboot.learning.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Builder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.*;

@RestController
//@Validated  /* To enable the controller level JSON Validation*/
@Api(value = "/employee", produces = "application/json", consumes = "application/json")
/*@Profile(value = "dev")*/
public class EmployeeRestController {
    private static final Logger loggerFactory = getLogger(EmployeeRestController.class);

    private IEmployeeService iEmployeeService;

    private JsonUtils jsonUtils;
    @Autowired
    public EmployeeRestController(EmployeeServiceImpl employeeServiceImpl, JsonUtils jsonUtils) {
        this.iEmployeeService = employeeServiceImpl;
        this.jsonUtils=jsonUtils;
    }

    @InitBinder
    public void initBinder(final DataBinder dataBinder) {
        dataBinder.setDisallowedFields("");
        dataBinder.setIgnoreInvalidFields(false);
    }

    @ApiOperation(value = "View a list of available employees", response = EmployeeList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @GetMapping(path = "/getAllEmployees", produces = "application/json")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeList() {
        List<EmployeeModel> employeeModelListRsp= EmployeeEntityToModelMapper.mapGetAllEmployeeQueryResultToModel(iEmployeeService.getAllEmployees()) ;
       // if(log.isInfoEnabled()) {
      //      loggerFactory.info("getEmployeeList ()--->Response" + jsonUtils.writeAsJson(employeeModelListRsp));
           // log.error("LOMBOK:--->Response"+ jsonUtils.writeAsJson(employeeModelListRsp));

       // }
        if (employeeModelListRsp.size() == 0) {
           // if(log.isErrorEnabled())
             //   log.error("LOMBOK:No data found in Database");
            throw new EmployeeNotFoundException("Bad Data", "No data found in Database");
        }
        return new ResponseEntity(employeeModelListRsp, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a employee by firstName/lastName", response = EmployeeList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )


    @GetMapping(path = "/getByEmployeeName/{firstName}/{lastName}", produces = "application/json")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByName(@PathVariable @Valid @Min(value = 1, message = "Path Param valiation:fristName must be greater than or equal to 1") String firstName, @PathVariable String lastName) {
       // if(log.isInfoEnabled())
        //    loggerFactory.info("getEmployeeByName ()--->Request :" + firstName + ",lastName" + lastName);

        List<EmployeeModel> employeeModelListRsp= EmployeeEntityToModelMapper.mapGetAllEmployeeQueryResultToModel(iEmployeeService.findByUserName(firstName, lastName)) ;

             if (employeeModelListRsp.size() == 0) {
             //    if(log.isDebugEnabled())
            //log.error("LOMBOK:Data not found in Database for matching input firatName:" + firstName + ",lastName:" + lastName);
            throw new EmployeeNotFoundException("Bad Data", "Data not found in Database for matching input firatName:" + firstName + ",lastName:" + lastName);
        }
      //  loggerFactory.info("getEmployeeByName ()--->Response" + jsonUtils.writeAsJson(employeeModelListRsp));
        return new ResponseEntity(employeeModelListRsp, HttpStatus.OK);

    }

    @ApiOperation(value = "Persistance employee details to Database", response = EmployeeList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved  employee list to database"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )


    @PostMapping(path = "/addEmployee", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> storeEmployee(@RequestBody @Valid List<EmployeeModel> employeeModelListReq) {
        final List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
        System.out.println("storeEmployee ()--->Request :" + jsonUtils.writeAsJson(employeeModelListReq));
           loggerFactory.debug("storeEmployee ()--->Request :" + jsonUtils.writeAsJson(employeeModelListReq));
        loggerFactory.debug("LOMBOK:storeEmployee ()--->Request :" + jsonUtils.writeAsJson(employeeModelListReq));

            employeeModelListReq.forEach(employeeModel -> {
                        employeeEntityList.add(EmployeeEntity.builder().uuid(UUID.randomUUID().toString()).id(employeeModel.getId()).firstName(employeeModel.getFirstName()).lastName(employeeModel.getLastName()).email(employeeModel.getEmail()).build());

         });
        List<EmployeeModel> employeeModelListRsp= EmployeeEntityToModelMapper.mapGetAllEmployeeQueryResultToModel(iEmployeeService.saveEmployee(employeeEntityList)) ;

        String responseAsString = new JsonUtils().writeAsJson(employeeModelListRsp);
        System.out.println("storeEmployee ()--->Request :" + jsonUtils.writeAsJson(employeeModelListRsp));

        loggerFactory.debug("storeEmployee()--->Response" + responseAsString);
        loggerFactory.debug("LOMBOK:storeEmployee()--->Response" + responseAsString);

      /* }catch(Exception ex){
           loggerFactory.error("Exception",ex);
          throw new InternalException(ex.getLocalizedMessage(),ex);

       }*/
        return new ResponseEntity(employeeModelListRsp, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
