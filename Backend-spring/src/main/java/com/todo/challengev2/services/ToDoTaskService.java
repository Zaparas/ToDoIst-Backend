package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;

import java.util.*;

public interface ToDoTaskService {
    ToDoTask convertToEntity(ToDoTaskDTO toDoTaskDTO);
    Collection<ToDoTaskDTO> getAllTasks();
    ToDoTask getById(UUID id);
    ToDoTaskDTO createTask(ToDoTaskDTO task);
    ToDoTaskDTO updateTask(ToDoTaskDTO task, UUID id);
    void deleteTask(UUID id);

}
