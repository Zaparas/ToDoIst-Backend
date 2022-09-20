package com.todo.challengev2.tasks.functionalities.convertToEntity;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;

public interface TaskConvertToEntityService {

    Task convertToEntity(TaskInDTO taskInDTO);
}
