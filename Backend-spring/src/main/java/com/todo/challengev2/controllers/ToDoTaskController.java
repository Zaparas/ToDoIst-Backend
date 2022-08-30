package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.serviceimpl.ToDoTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
public class ToDoTaskController {

    public final ToDoTaskServiceImpl taskService;

    //@TODO: CORS cross origin resource sharing - should be set to allow on the server side
    @GetMapping
    public List<ToDoTask> getAllTasks(){ // @TODO: return in json format for Rest Service , not MVC or Thymeleaf
        return taskService.getAllTasks();
    }

//    @RequestMapping({"/view/{uuid}"})
//    public void viewTask(@PathVariable UUID uuid){
//        taskService.getById(uuid);
//    }
//
//
//    @RequestMapping({"/delete/{uuid}"})
//    public void deleteTask(@PathVariable UUID uuid){
//        taskService.deleteTask(uuid);
//    }


}
