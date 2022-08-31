package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.repositories.ToDoTaskRepository;
import com.todo.challengev2.services.ToDoTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoTaskServiceImpl implements ToDoTaskService {

    private final ToDoTaskRepository repository;

//    @Override
//    public void createTask(ToDoTask task) {
//        tasks.put(task.getId(),task);
//    }
//
//    @Override
//    public void updateTask(UUID id, ToDoTask task) {
//        tasks.replace(id,task);
//    }
//
//    @Override
//    public void deleteTask(UUID id) {
//        tasks.remove(id);
//    }

    @Override
    public List<ToDoTask> getAllTasks() {

        return repository.findAll();
    }

    @Override
    public Optional<ToDoTask> getById(UUID id){
        return repository.findById(id);
    }
}
