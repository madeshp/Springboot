package com.springboot.learning.RestTemplateClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTodo {
    private static final long serialVersionUID = 8987650490058676581L;

    private int userId;
    private int id;
    private String title;
    private String  completed;
}
