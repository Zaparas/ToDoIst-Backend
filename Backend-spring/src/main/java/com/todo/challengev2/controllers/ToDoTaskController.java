package com.todo.challengev2.controllers;

import com.todo.challengev2.serviceimpl.ToDoTaskServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/tasks")
@RestController
public class ToDoTaskController {

    public final ToDoTaskServiceImpl taskService;

    public ToDoTaskController(ToDoTaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    //@TODO: CORS cross origin resource sharing - should be set to allow on the server side
    @RequestMapping({"","/","/index","/index/"})
    public String getAllTasks(Model model){ // @TODO: return in json format for Rest Service , not MVC or Thymeleaf

        model.addAttribute(taskService.getAllTasks());

        return "index";
    }

    @RequestMapping({"/view/{uuid}"})
    public void viewTask(@PathVariable UUID uuid){
        taskService.getById(uuid);
    }


    @RequestMapping({"/delete/{uuid}"})
    public void deleteTask(@PathVariable UUID uuid){
        taskService.deleteTask(uuid);
    }


}
