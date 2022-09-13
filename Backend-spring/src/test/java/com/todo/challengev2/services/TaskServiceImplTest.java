package com.todo.challengev2.services;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.todo.challengev2.config.util.PriorityType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    TaskServiceImpl service;

    @Mock
    TaskRepository repo;

    @Test
    void convertToEntity() {
    }

    @Test
    void getAllTasks() {

        List<TaskOutDTO> listRes;
        List<Task> listData = new ArrayList<>();

        listData.add(new Task("clean test", LocalDate.now(),HIGH));
        listData.add(new Task("jumps test", LocalDate.now().plusDays(1),LOW));
        listData.add(new Task("dance test", LocalDate.now().plusWeeks(1),MID));

        Mockito.doReturn(listData).when(repo).findAll();

        listRes = service.getAllTasks();

        assertEquals(3,listRes.size());
        verify(repo, times(1)).findAll();

    }

    @Test
    void getById() {

        Task temp = new Task("jumps test", LocalDate.now().plusDays(1),LOW);
        TaskOutDTO res;

        List<Task> listData = new ArrayList<>();
        listData.add(new Task("clean test", LocalDate.now(),HIGH));
        listData.add(temp);
        listData.add(new Task("dance test", LocalDate.now().plusWeeks(1),MID));

        //TODO: must set mockito return command here for repo probably best to delete this command and write a new one
        // Mockito.when(repo.findById(temp.getId()).thenReturn(temp));

        res = service.getById(listData.get(1).getId());

        assertEquals(res,new TaskOutDTO(temp));
        verify(repo, times(1)).findAll();
    }

    @Test
    void createTask() {
    }
}