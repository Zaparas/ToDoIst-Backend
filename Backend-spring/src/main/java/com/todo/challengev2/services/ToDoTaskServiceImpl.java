package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import lombok.RequiredArgsConstructor;
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
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final ToDoTaskRepository repository;

    @Override
    public ToDoTask dtoToEntity(ToDoTaskDTO task){
        ToDoTask toDoTask = new ToDoTask();
        BeanUtils.copyProperties(task, toDoTask);
        return toDoTask;
    }

    @Override
    public void createTask(ToDoTaskDTO task) {repository.save(dtoToEntity(task));}

    @Override
    public void updateTask(ToDoTaskDTO task) {
        ToDoTask target = repository.findById(task.getId()).orElse(null);
        if(target!=null) {
            target.setDueDate(task.getDueDate());
            target.setName(task.getName());
            target.setPriority(task.getPriority());
            repository.save(target);
        }
        repository.save(dtoToEntity(task));
    }

    @Override
    public void deleteTask(UUID id) {
        repository.deleteById(id);
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
    public ToDoTask getById(UUID id) {
        Optional<ToDoTask> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

           return repository.findById(id).get();
    }

    @Override
    public ToDoTaskDTO save(ToDoTaskDTO toDoTaskDTO) {
        return new ToDoTaskDTO(repository.save(dtoToEntity(toDoTaskDTO)));
    }
}
