package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final ToDoTaskRepository repository;

    @Override
    public void createTask(ToDoTask task) {
        repository.save(task);
    }

    @Override
    public void updateTask(ToDoTask task, UUID id) {

        ToDoTask target = repository.findById(id).orElse(null);
        if(target!=null) {
            target.setDueDate(task.getDueDate());
            target.setName(task.getName());
            target.setPriority(task.getPriority());
            repository.save(target);
        }

    }

    @Override
    public void deleteTask(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<ToDoTask> getAllTasks() {

        return repository.findAll();
    }

    @Override
    public ToDoTask getById(UUID id){
        Optional<ToDoTask> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return repository.findById(id).get();
    }
}
