package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;

import java.util.*;

public interface ToDoTaskService {

    ToDoTask dtoToEntity(ToDoTaskDTO task);

    void createTask(ToDoTaskDTO task);

    void updateTask(ToDoTaskDTO task);

    void deleteTask(UUID id);

    Collection<ToDoTaskDTO> getAllTasks();

    ToDoTask getById(UUID id);

    /** Εναλλακτική μέθοδος για να κάνει save και update */
    ToDoTaskDTO save(ToDoTaskDTO toDoTaskDTO);
}
