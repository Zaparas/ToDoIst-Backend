package com.todo.challengev2.tasks.functionalities.list;

import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;

import java.util.List;

public interface TaskListService {

    List<TaskOutDTO> list();
}
