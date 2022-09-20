package com.todo.challengev2.tasks.functionalities.search;

import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

import java.util.List;

public interface TaskSearchService {

    List<TaskOutDTO> searchTask(TaskIndexDTO taskIndexDTO);
}
