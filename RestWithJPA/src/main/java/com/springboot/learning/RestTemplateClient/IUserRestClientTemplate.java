package com.springboot.learning.RestTemplateClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public interface IUserRestClientTemplate
{
    public ResponseEntity<UserTodo[]> getUserTodo();
}
