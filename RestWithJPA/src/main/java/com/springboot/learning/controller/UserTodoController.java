package com.springboot.learning.controller;

import com.springboot.learning.RestTemplateClient.IUserRestClientTemplate;
import com.springboot.learning.RestTemplateClient.UserTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTodoController {
    @Autowired
    private IUserRestClientTemplate iUserRestTemplateClient;
@GetMapping(value = "/user/todos",produces = "application/json")
    public ResponseEntity<UserTodo[]> getUserTodo(){
return iUserRestTemplateClient.getUserTodo();

}
}
