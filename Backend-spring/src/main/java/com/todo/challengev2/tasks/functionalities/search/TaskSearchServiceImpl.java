package com.todo.challengev2.tasks.functionalities.search;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskSearchServiceImpl implements TaskSearchService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskFullDTO> searchTask(TaskIndexDTO taskIndexDTO) {
        List<TaskFullDTO> list = new ArrayList<>();
        for (Task task : taskRepository.search(taskIndexDTO)) {
            list.add(new TaskFullDTO(task));
        }
        return list;
    }
}
