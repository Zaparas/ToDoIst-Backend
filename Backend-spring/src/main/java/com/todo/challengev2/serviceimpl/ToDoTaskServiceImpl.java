package com.todo.challengev2.serviceimpl;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.services.ToDoTaskService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Service
public class ToDoTaskServiceImpl implements ToDoTaskService {

    @Override
    public void createTask(ToDoTask task) {
        tasks.put(task.getId(),task);
    }

    @Override
    public void updateTask(UUID id, ToDoTask task) {
        tasks.replace(id,task);
    }

    @Override
    public void deleteTask(UUID id) {
        tasks.remove(id);
    }

    @Override
    public Collection<ToDoTask> getAllTasks() {

        Collection<ToDoTask> result = new HashSet<>();
        tasks.forEach( (uuid, toDoTask) -> {result.add(toDoTask); });
        return result;
    }

    @Override
    public ToDoTask getById(UUID id){
        return tasks.get(id);
    }
}
