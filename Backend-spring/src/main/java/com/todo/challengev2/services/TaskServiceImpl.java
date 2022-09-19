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

/**
 * Implements Service Interface, includes the CRUD methods for Task Entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    /**
     * Imports JPA repository
     */
    private final TaskRepository repository;

    /**
     * This method converts our input DTO (TaskInDTO) to an Entity, so we can use it on JPA's methods.
     * @param taskInDTO our DTO with the data that we want to convert to Entity.
     * @return a Task Entity.
     */
    @Override
    public Task convertToEntity(TaskInDTO taskInDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskInDTO, task);
        return task;
    }

    /**
     * This method converts to TaskOutDTO and fetches all the tasks, stored in our database.
     * @return a List of TaskOutDTO.
     */
    @Override
    public List<TaskOutDTO> getAllTasks() {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : repository.findAll()) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }

    /**
     * This method uses JPA's .findById() method, to fetch a specific task according to a given ID.
     * @param id, the requested ID (type of UUID).
     * @return the task with this ID, if is found.
     * @throws RuntimeException, if given ID is not found.
     */
    @Override
    public TaskOutDTO getById(UUID id) throws RuntimeException{
        Optional<Task> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return new TaskOutDTO(optional.get());
    }

    /**
     * This method creates a new Task and stores it in our database using JPA's .save() method.
     * @param taskInDTO our DTO with data of a new Task.
     * @return a TaskOutDTO, representing the saved Task.
     */
    @Override
    public TaskOutDTO createTask(TaskInDTO taskInDTO) {
        return new TaskOutDTO(repository.save(convertToEntity(taskInDTO)));
    }

    /**
     * This method searches for a given ID and if a task is found, updates it, using JPA's .save() method and a
     *  TaskInDTO as input.
     * @param newTask, our DTO with new data that we want to be stored in database.
     * @param id, the requested ID (type of UUID).
     * @return a TaskOutDTO representing the updated Task.
     * @throws RuntimeException, if given ID is not found.
     */
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

    /**
     * This method deletes a Task, according to a given ID.
     * @param id, the requested ID (type of UUID).
     */
    @Override
    public void deleteTask(UUID id){
        if (getById(id) != null) {
            repository.deleteById(id);
        }
    }

    /**
     * This method searches for Tasks, according to given criteria of our TaskIndexDTO. We can search a task,
     * according to id, name, afterDate, beforeDate, priority.
     * @param taskIndexDTO a DTO representing out search criteria.
     * @return a list with TaskOutDTO's, according to search's criteria.
     */
    @Override
    public List<TaskOutDTO> searchTask(TaskIndexDTO taskIndexDTO) {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : repository.search(taskIndexDTO)) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }
}