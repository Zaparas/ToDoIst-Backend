package com.todo.challengev2.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Getter
public class RestServiceConfiguration {

    @Value("${todo-app.cors.enabled:false}")
    private boolean corsEnabled;
    @Value("http://localhost:4200")
    private List<String> corsAllowedOrigins = new ArrayList<>();
    @Value("${todo-app.cors.allowed-headers:}")
    private List<String> corsAllowedHeaders = new ArrayList<>();

}
