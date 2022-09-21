package com.todo.challengev2.tasks.functionalities.list;

import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;

import java.util.List;

public interface TaskListService {

    List<TaskFullDTO> list();
}
