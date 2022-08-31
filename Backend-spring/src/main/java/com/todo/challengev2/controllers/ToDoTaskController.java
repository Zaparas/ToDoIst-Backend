package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.services.ToDoTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
public class ToDoTaskController {

    public final ToDoTaskServiceImpl taskService;

    //@TODO: CORS cross origin resource sharing - should be set to allow on the server side
    //is now allowed on the server side see SecurityConfigurer
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
