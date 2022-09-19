package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.domain.Task;
import com.todo.challengev2.repositories.RelationRepository;
import com.todo.challengev2.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationController {

    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Relation> list() {
        if (relationRepository.findAll().isEmpty()) {
            return null;
        }
        return relationRepository.findAll();
    }

    @GetMapping("/test")
    public List<Task> list1() {
        return taskRepository.findAll();
    }
}
