package com.todo.challengev2.services;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskInDTO;
import com.todo.challengev2.dto.TaskOutDTO;

import java.util.*;

public interface TaskService {
    Task convertToEntity(TaskInDTO toDoTaskDTO);
    Collection<TaskOutDTO> getAllTasks();
    TaskOutDTO getById(UUID id);
    TaskOutDTO createTask(TaskInDTO task);
    TaskOutDTO updateTask(TaskInDTO task, UUID id);
    void deleteTask(UUID id);

}
