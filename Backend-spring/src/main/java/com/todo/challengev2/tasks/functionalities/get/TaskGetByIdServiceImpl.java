package com.todo.challengev2.tasks.functionalities.get;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskGetByIdServiceImpl implements TaskGetByIdService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskOutDTO get(UUID id) {
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return new TaskOutDTO(optional.get());
    }
}
