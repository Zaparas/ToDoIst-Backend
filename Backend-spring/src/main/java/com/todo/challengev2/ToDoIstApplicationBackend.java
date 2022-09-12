package com.todo.challengev2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ToDoIstApplicationBackend {

    public static void main(String[] args) {
        SpringApplication.run(ToDoIstApplicationBackend.class, args);
    }

}
