package com.todo.challengev2.tasks.functionalities.get;

import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

import java.util.UUID;

public interface TaskGetByIdService {

    TaskOutDTO get(UUID id);
}
