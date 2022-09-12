package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskInputDTO;
import com.todo.challengev2.dto.ToDoTaskOutputDTO;
import com.todo.challengev2.repositories.ToDoTaskRepository;
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
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final ToDoTaskRepository repository;

    @Override
    public ToDoTask convertToEntity(ToDoTaskInputDTO toDoTaskDTO) {
        ToDoTask task = new ToDoTask();
        BeanUtils.copyProperties(toDoTaskDTO, task);
        return task;
    }

    @Override
    public List<ToDoTaskOutputDTO> getAllTasks() {
        List<ToDoTaskOutputDTO> list = new ArrayList<>();
        for (ToDoTask task : repository.findAll()) {
            list.add(new ToDoTaskOutputDTO(task));
        }
        return list;
    }

    @Override
    public ToDoTaskOutputDTO getById(UUID id) throws RuntimeException{
        Optional<ToDoTask> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return new ToDoTaskOutputDTO(repository.findById(id).get());
    }

    @Override
    public ToDoTaskOutputDTO createTask(ToDoTaskInputDTO task) {
        ToDoTask entity = convertToEntity(task);
        ToDoTask savedEntity = repository.save(entity);
        return new ToDoTaskOutputDTO(repository.save(convertToEntity(task)));
    }

    @Override
    public ToDoTaskOutputDTO updateTask(ToDoTaskInputDTO newTask, UUID id) throws RuntimeException {
        // TODO: 12/9/2022 Should we use repository's "findById()" or service's "getById()" ?
        Optional<ToDoTask> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        BeanUtils.copyProperties(newTask, optional.get());
        return new ToDoTaskOutputDTO(repository.save(optional.get()));
    }

    @Override
    public void deleteTask(UUID id){
        if (getById(id) != null) {
            repository.deleteById(id);
        }
    }
}
