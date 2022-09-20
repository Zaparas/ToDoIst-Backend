package com.todo.challengev2.tasks.functionalities.update;

import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

import java.util.UUID;

public interface TaskUpdateService {

    TaskOutDTO updateTask(UUID id, TaskInDTO taskInDTO);
}
