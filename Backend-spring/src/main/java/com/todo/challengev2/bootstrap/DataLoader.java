package com.todo.challengev2.bootstrap;


import com.todo.challengev2.tasks.functionalities.list.TaskListService;
import com.todo.challengev2.tasks.utils.enums.PriorityType;
import com.todo.challengev2.relations.utils.enums.RelationType;
import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
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
    private TaskListService taskListService;
    @Bean
    CommandLineRunner initDatabase(TaskRepository repository){

        if (taskListService.list().isEmpty()) {
            return args -> {
                Task taskCode = repository.save(new Task(
                        "Code",
                        LocalDate.now().plusDays(1),
                        PriorityType.HIGH,
                        "Lorem Ipsum Description"));
                Task taskMeeting = repository.save(new Task(
                        "Code Meeting",
                        LocalDate.now().plusDays(2),
                        PriorityType.LOW, "Random Text for Description",
                        Arrays.asList(new Relation(RelationType.PARENT, taskCode))));
                Task taskCoffee = repository.save(new Task(
                        "Make Coffee",
                        LocalDate.now(),
                        PriorityType.LOW,
                        "Some description here",
                        Arrays.asList(new Relation(RelationType.DEPENDENT, taskMeeting))));
                Task taskTask = repository.save(new Task(
                        "Task Coffee",
                        LocalDate.now(),
                        PriorityType.LOW,
                        "Some something here",
                        Arrays.asList(new Relation(RelationType.DEPENDENT, taskCode))));
                log.info("Number of total entries is: #" + taskListService.list().size());
            };
        }
        return args -> log.info("Database already initialized. Number of total entries is: #" + taskListService.list().size());
    }
}