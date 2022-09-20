package com.todo.challengev2.tasks.functionalities.delete;

import com.todo.challengev2.tasks.functionalities.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskDeleteServiceImpl implements TaskDeleteService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
