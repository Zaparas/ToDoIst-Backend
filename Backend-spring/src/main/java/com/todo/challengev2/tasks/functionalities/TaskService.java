package com.todo.challengev2.tasks.functionalities;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

import java.util.*;

public interface TaskService {
    Task convertToEntity(TaskInDTO taskInDTO);
    Collection<TaskOutDTO> getAllTasks();
    TaskOutDTO getById(UUID id);
    TaskOutDTO createTask(TaskInDTO taskInDTO);
    TaskOutDTO updateTask(TaskInDTO task, UUID id);
    void deleteTask(UUID id);
    List<TaskOutDTO> searchTask(TaskIndexDTO taskIndexDTO);

}
