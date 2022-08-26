package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface ToDoTaskService {

    public final Map<UUID, ToDoTask> tasks = new HashMap<>();

    public void createTask(ToDoTask task);
    public void updateTask(UUID id, ToDoTask task);
    public void deleteTask(UUID id);
    public Collection<ToDoTask> getAllTasks();

    public ToDoTask getById(UUID id);
}
