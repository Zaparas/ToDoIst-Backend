package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;

import java.util.*;

public interface ToDoTaskService {

    ToDoTaskDTO ConvertToDTo(ToDoTask task);

    ToDoTask convertToEntity(ToDoTaskDTO toDoTaskDTO);
    ToDoTaskDTO createTask(ToDoTaskDTO task);
    ToDoTaskDTO updateTask(ToDoTaskDTO task, UUID id);
    void deleteTask(UUID id);
    Collection<ToDoTaskDTO> getAllTasks();
    ToDoTask getById(UUID id);
}
