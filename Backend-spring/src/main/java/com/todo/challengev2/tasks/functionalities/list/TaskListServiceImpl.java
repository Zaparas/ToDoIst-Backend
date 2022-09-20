package com.todo.challengev2.tasks.functionalities.list;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskOutDTO> list() {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }
}
