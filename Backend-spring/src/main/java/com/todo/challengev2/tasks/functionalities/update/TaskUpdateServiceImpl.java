package com.todo.challengev2.tasks.functionalities.update;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskOutDTO updateTask(UUID id, TaskInDTO taskInDTO) {
        // TODO: 12/9/2022 Should we use repository's "findById()" or service's "getById()" ?
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        BeanUtils.copyProperties(taskInDTO, optional.get());
        return new TaskOutDTO(taskRepository.save(optional.get()));
    }
}
