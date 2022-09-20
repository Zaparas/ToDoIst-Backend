package com.todo.challengev2.tasks.functionalities.create;

import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.functionalities.convertToEntity.TaskConvertToEntityService;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCreateServiceImpl implements TaskCreateService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskConvertToEntityService taskConvertToEntityService;

    @Override
    public TaskOutDTO createTask(TaskInDTO taskInDTO) {
        return new TaskOutDTO(taskRepository.save(taskConvertToEntityService.convertToEntity(taskInDTO)));
    }
}