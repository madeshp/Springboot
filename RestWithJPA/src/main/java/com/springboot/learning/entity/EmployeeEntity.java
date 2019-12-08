package com.springboot.learning.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
@Entity
@Table(name = "employee")
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString/* @Data - Generates setter, getter, no argconst,argconst,to string and so on in the .class file using LOMBOX framework */
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "firstName")
 /*   @NotNull( message="{user.firstName.notNull}")
    @Size(min = 1, max = 10, message = "{user.lastName.size}")*/
      private String firstName;
    @Column(name = "lastName")
   /* @NotNull(message = "{user.lastName.notNull}")
    @Size(min = 1, max = 10, message = "{user.lastName.size}")*/
    private String lastName;
    @Column(name = "email")
    private String email;

}
