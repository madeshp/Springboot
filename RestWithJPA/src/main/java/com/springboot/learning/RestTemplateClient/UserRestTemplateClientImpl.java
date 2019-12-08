package com.springboot.learning.RestTemplateClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestTemplateClientImpl implements IUserRestClientTemplate {
    @Override
    public ResponseEntity<UserTodo[]> getUserTodo(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://jsonplaceholder.typicode.com/todos";
        ResponseEntity<UserTodo[]> response
                = restTemplate.getForEntity(fooResourceUrl , UserTodo[].class);
return response;
    }

}
