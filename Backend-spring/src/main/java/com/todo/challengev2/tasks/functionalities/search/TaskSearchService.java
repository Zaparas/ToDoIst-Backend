package com.todo.challengev2.tasks.functionalities.search;

import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;

import java.util.List;

public interface TaskSearchService {

    List<TaskFullDTO> searchTask(TaskIndexDTO taskIndexDTO);
}
