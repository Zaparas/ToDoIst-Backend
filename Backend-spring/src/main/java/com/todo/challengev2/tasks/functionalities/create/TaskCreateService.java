package com.todo.challengev2.tasks.functionalities.create;

import com.todo.challengev2.tasks.utils.dtos.TaskRestrictedDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;

public interface TaskCreateService {

    TaskFullDTO createTask(TaskRestrictedDTO taskRestrictedDTO);
}
