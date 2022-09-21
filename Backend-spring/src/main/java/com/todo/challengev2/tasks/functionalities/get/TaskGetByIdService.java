package com.todo.challengev2.tasks.functionalities.get;

import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;

import java.util.UUID;

public interface TaskGetByIdService {

    TaskFullDTO get(UUID id);
}
