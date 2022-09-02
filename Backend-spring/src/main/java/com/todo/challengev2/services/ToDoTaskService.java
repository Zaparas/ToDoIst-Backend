package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;

import java.util.*;

public interface ToDoTaskService {

    void createTask(ToDoTask task);
    void updateTask(ToDoTask task, UUID id);
    void deleteTask(UUID id);
    Collection<ToDoTask> getAllTasks();
    ToDoTask getById(UUID id);
}
