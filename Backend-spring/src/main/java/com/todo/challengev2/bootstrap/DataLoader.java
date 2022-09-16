package com.todo.challengev2.bootstrap;


import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.config.util.RelationType;
import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.domain.Task;
import com.todo.challengev2.repositories.RelationRepository;
import com.todo.challengev2.repositories.TaskRepository;
import com.todo.challengev2.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Slf4j
public class DataLoader {

    @Autowired
    private TaskService service;

//    @Autowired
//    private RelationRepository relations;

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository, RelationRepository relationRepository) {
//        repository.deleteAll();
        if (service.getAllTasks().isEmpty()) {
            return args -> {
                Task task = repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH, "Lorem Ipsum Description"));
//              log.info("Log - Preloading - " + repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH, "Lorem Ipsum Description")));
              log.info("Log - Preloading - " + repository.save(new Task("Team Meeting", LocalDate.now().plusDays(2), PriorityType.LOW, "Random Text for Description", Arrays.asList(new Relation(RelationType.PARENT, task)))));
              log.info("Log - Preloading - " + repository.save(new Task("Make Coffee", LocalDate.now(), PriorityType.LOW, "Some description here", Arrays.asList(new Relation(RelationType.DEPENDENT, task)))));

//                Task task1 = repository.save(new Task("Code", LocalDate.now().plusDays(1), PriorityType.HIGH, "Lorem Ipsum Description"));
//                Task task2 = repository.save(new Task("Code2", LocalDate.now().plusDays(1), PriorityType.MID, "Lorem Ipsum Description"));
//                Relation relation = relationRepository.save(new Relation(RelationType.PARENT,task2));
//                task1.setRelations(Arrays.asList(relation));

                log.info("Data init complete");



            };
        }
        log.info(repository.findAll().stream().toString());
        return args -> log.info("Database already initialized. Number of total entries is: #" + service.getAllTasks().size());
    }
}
