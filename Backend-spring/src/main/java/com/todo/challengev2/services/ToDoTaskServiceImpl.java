package com.todo.challengev2.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final ToDoTaskRepository repository;

    @Override
    public ToDoTask convertToEntity(ToDoTaskDTO toDoTaskDTO) {
        ToDoTask task = new ToDoTask();
        BeanUtils.copyProperties(toDoTaskDTO, task);
        return task;
    }

    @Override
    public List<ToDoTaskDTO> getAllTasks() {
        List<ToDoTaskDTO> list = new ArrayList<>();
        for (ToDoTask task : repository.findAll()) {
            list.add(new ToDoTaskDTO(task));
        }
        return list;
    }

    @Override
    public ToDoTask getById(UUID id) throws RuntimeException{
        Optional<ToDoTask> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return repository.findById(id).get();
    }

    @Override
    public ToDoTaskDTO createTask(ToDoTaskDTO task) {
        ToDoTask entity = convertToEntity(task);
        ToDoTask savedEntity = repository.save(entity);
        return new ToDoTaskDTO(repository.save(convertToEntity(task)));
    }

    @Override
    public ToDoTaskDTO updateTask(ToDoTaskDTO newTask, UUID id) throws RuntimeException {
        ToDoTask target = repository.findById(id).orElse(null);
        BeanUtils.copyProperties(newTask, target);
        return new ToDoTaskDTO(repository.save(convertToEntity(newTask)));
    }

    @Override
    public void deleteTask(UUID id){
        if (getById(id) != null) {
            repository.deleteById(id);
        }
    }
}
