package com.todo.challengev2.bootstrap;


import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.functionalities.list.RelationListServiceImpl;
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
import java.util.List;

@Configuration
@Slf4j
public class DataLoader {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private RelationListServiceImpl relationListService;

    /**
     * This method initializes the database if it is found to have zero entries. Additionally, it always outputs a
     *      * message using Long.info including the total number of entries detected in the database
     * @param taskRepository the taskRepository bound by spring to this instance
     * @return the code to be run during the initialization
     */
    @Bean
    CommandLineRunner initDatabase(TaskRepository taskRepository){

        if (taskListService.list().isEmpty()) {
            return args -> {
                Task taskCode = taskRepository.save(new Task(
                        "Code",
                        LocalDate.now().plusDays(1),
                        PriorityType.HIGH,
                        "Lorem Ipsum Description"));
                Task taskMeeting = taskRepository.save(new Task(
                        "Code Meeting",
                        LocalDate.now().plusDays(2),
                        PriorityType.LOW, "Random Text for Description",
                        List.of(new Relation(RelationType.PARENT, taskCode))));
                Task taskCoffee = taskRepository.save(new Task(
                        "Make Coffee",
                        LocalDate.now(),
                        PriorityType.LOW,
                        "Some description here",
                        List.of(new Relation(RelationType.DEPENDENT, taskMeeting))));
                Task taskTask = taskRepository.save(new Task(
                        "Task Coffee",
                        LocalDate.now(),
                        PriorityType.LOW,
                        "Some something here",
                        List.of(new Relation(RelationType.DEPENDENT, taskCode))));
                log.info("LOGGER:: Database Initialized.");
                log.info("LOGGER:: Database Task Table number of new entries is     : #" + taskListService.list().size());
                log.info("LOGGER:: Database Relation Table number of new entries is : #" + relationListService.list().size());
            };
        }
        return args -> log.info("LOGGER:: Database already initialized. Number of total entries is: #" + (taskListService.list().size()+ relationListService.list().size()));
    }
}