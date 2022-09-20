package com.todo.challengev2.tasks.functionalities.create;

import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

public interface TaskCreateService {

    TaskOutDTO createTask(TaskInDTO taskInDTO);
}
