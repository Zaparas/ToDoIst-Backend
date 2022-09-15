package com.todo.challengev2.bootstrap;


import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.config.util.RelationType;
import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.domain.Task;
import com.todo.challengev2.repositories.TaskRepository;
import com.todo.challengev2.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class DataLoader {

    @Autowired
    private TaskService service;
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository){
        repository.deleteAll();
        if (service.getAllTasks().isEmpty()) {
            return args -> {
                Task task = repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH, "Lorem Ipsum Description"));
//                log.info("Log - Preloading - " + repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH, "Lorem Ipsum Description")));
                log.info("Log - Preloading - " + repository.save(new Task("Team Meeting", LocalDate.now().plusDays(2), PriorityType.LOW, "Random Text for Description", Arrays.asList(new Relation(RelationType.PARENT, task)))));
                log.info("Log - Preloading - " + repository.save(new Task("Make Coffee", LocalDate.now(), PriorityType.LOW, "Some description here",  Arrays.asList(new Relation(RelationType.DEPENDENT, task)))));
            };
        }
        log.info(repository.findAll().stream().toString());
        return args -> log.info("Database already initialized. Number of total entries is: #" + service.getAllTasks().size());
    }
}
