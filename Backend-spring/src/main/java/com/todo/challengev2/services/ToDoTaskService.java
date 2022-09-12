package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskInputDTO;
import com.todo.challengev2.dto.ToDoTaskOutputDTO;

import java.util.*;

public interface ToDoTaskService {
    ToDoTask convertToEntity(ToDoTaskInputDTO toDoTaskDTO);
    Collection<ToDoTaskOutputDTO> getAllTasks();
    ToDoTaskOutputDTO getById(UUID id);
    ToDoTaskOutputDTO createTask(ToDoTaskInputDTO task);
    ToDoTaskOutputDTO updateTask(ToDoTaskInputDTO task, UUID id);
    void deleteTask(UUID id);

}
