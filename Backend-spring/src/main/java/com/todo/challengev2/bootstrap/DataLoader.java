package com.todo.challengev2.bootstrap;


import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.domain.Task;
import com.todo.challengev2.repositories.TaskRepository;
import com.todo.challengev2.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
@Slf4j
public class DataLoader {

    @Autowired
    private TaskService service;
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository){

        if (service.getAllTasks().isEmpty()) {
            return args -> {
                log.info("Log - Preloading - " + repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH )));
                log.info("Log - Preloading - " + repository.save(new Task("Team Meeting", LocalDate.now().plusDays(2), PriorityType.LOW )));
                log.info("Log - Preloading - " + repository.save(new Task("Make Coffee", LocalDate.now(), PriorityType.LOW )));
            };
        }
        return args -> log.info("Database already initialized. Number of total entries is: #" + service.getAllTasks().size());
    }
}
