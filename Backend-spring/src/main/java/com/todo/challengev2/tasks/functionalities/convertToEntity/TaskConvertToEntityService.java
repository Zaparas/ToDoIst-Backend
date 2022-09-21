package com.todo.challengev2.tasks.functionalities.convertToEntity;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskRestrictedDTO;

public interface TaskConvertToEntityService {

    Task convertToEntity(TaskFullDTO taskFullDTO);

    Task convertToEntity(TaskRestrictedDTO taskRestrictedDTO);
}
