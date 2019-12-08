package com.springboot.learning.model;

import com.fasterxml.jackson.core.JsonParser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Data;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeModel {

    private Integer id;
    private String uuid;
    @NotNull(message="First name cannot be missing or empty")
    @Size(min=2, message="First name must not be less than 2 characters")
    private String firstName;
    @NotNull(message="Last name cannot be missing or empty")
    @Size(min=2, message="Last name must not be less than 2 characters")
    private String lastName;
    private String email;

}
