package com.todo.challengev2.services;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskInDTO;
import com.todo.challengev2.dto.TaskIndexDTO;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public Task convertToEntity(TaskInDTO toDoTaskDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(toDoTaskDTO, task);
        return task;
    }

    @Override
    public List<TaskOutDTO> getAllTasks() {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : repository.findAll()) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }

    @Override
    public TaskOutDTO getById(UUID id) throws RuntimeException{
        Optional<Task> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return new TaskOutDTO(repository.findById(id).get());
    }

    @Override
    public TaskOutDTO createTask(TaskInDTO task) {
        Task entity = convertToEntity(task);
        Task savedEntity = repository.save(entity);
        return new TaskOutDTO(repository.save(convertToEntity(task)));
    }

    @Override
    public TaskOutDTO updateTask(TaskInDTO newTask, UUID id) throws RuntimeException {
        // TODO: 12/9/2022 Should we use repository's "findById()" or service's "getById()" ?
        Optional<Task> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        BeanUtils.copyProperties(newTask, optional.get());
        return new TaskOutDTO(repository.save(optional.get()));
    }

    @Override
    public void deleteTask(UUID id){
        if (getById(id) != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public List<TaskOutDTO> searchTask(TaskIndexDTO taskIndexDTO) {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : repository.search(taskIndexDTO)) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }
}
