package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;
import com.todo.challengev2.services.ToDoTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
public class ToDoTaskController {

    public final ToDoTaskServiceImpl taskService;

    //@TODO: CORS cross origin resource sharing - should be set to allow on the server side
    //is now allowed on the server side see SecurityConfigurer
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDoTaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }


    @RequestMapping("/{id}")
    public ToDoTask getTask(@PathVariable UUID id){
        return taskService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void newTask(@RequestBody ToDoTask task){ taskService.createTask(task); }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateTask(@RequestBody ToDoTask task, @PathVariable UUID id){ taskService.updateTask(task,id); }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTask(@PathVariable UUID id){ taskService.deleteTask(id); }
}
