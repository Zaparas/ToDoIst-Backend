package com.todo.challengev2.tasks.functionalities.update;

import com.todo.challengev2.tasks.utils.dtos.TaskRestrictedDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;

import java.util.UUID;

public interface TaskUpdateService {

    TaskFullDTO updateTask(UUID id, TaskFullDTO taskFullDTO);
}
