package com.todo.challengev2.bootstrap;


import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
@Configuration
public class DataLoader {


    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(ToDoTaskRepository repository){
        return args -> {
            log.info("Log - Preloading - " + repository.save(new ToDoTask("Code", LocalDateTime.now().plusDays(1), ToDoTask.PriorityType.HIGH )));
            log.info("Log - Preloading - " + repository.save(new ToDoTask("Team Meeting", LocalDateTime.now().plusDays(2).plusHours(1), ToDoTask.PriorityType.LOW )));
            log.info("Log - Preloading - " + repository.save(new ToDoTask("Make Coffee", LocalDateTime.now().plusHours(1), ToDoTask.PriorityType.LOW )));
        };
    }
}
