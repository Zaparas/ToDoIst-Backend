package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;

import java.util.*;

public interface ToDoTaskService {

//    void createTask(ToDoTask task);
//    void updateTask(UUID id, ToDoTask task);
//    void deleteTask(UUID id);
    Collection<ToDoTask> getAllTasks();
    Optional<ToDoTask> getById(UUID id);
}
