package com.springboot.learning.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitTestController {
    @GetMapping(path = "/getUnitTestData" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUnitTestData(){

        return "{data:get unit test data}";
    }
}
