package com.todo.challengev2.bootstrap;


import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import com.todo.challengev2.services.ToDoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Configuration
@Slf4j
public class DataLoader {

    @Autowired
    private ToDoTaskService service;
    @Bean
    CommandLineRunner initDatabase(ToDoTaskRepository repository){

        if (service.getAllTasks().isEmpty()) {
            return args -> {
                log.info("Log - Preloading - " + repository.save(new ToDoTask("Code", LocalDate.now().plusDays(1), PriorityType.HIGH )));
                log.info("Log - Preloading - " + repository.save(new ToDoTask("Team Meeting", LocalDate.now().plusDays(2), PriorityType.LOW )));
                log.info("Log - Preloading - " + repository.save(new ToDoTask("Make Coffee", LocalDate.now(), PriorityType.LOW )));
            };
        }
        return args -> log.info("Database already initialized");
    }
}
