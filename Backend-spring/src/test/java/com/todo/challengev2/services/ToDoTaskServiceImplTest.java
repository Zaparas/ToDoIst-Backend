package com.todo.challengev2.services;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskOutputDTO;
import com.todo.challengev2.repositories.ToDoTaskRepository;
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
class ToDoTaskServiceImplTest {

    @InjectMocks
    ToDoTaskServiceImpl service;

    @Mock
    ToDoTaskRepository repo;

    @Test
    void convertToEntity() {
    }

    @Test
    void getAllTasks() {

        List<ToDoTaskOutputDTO> listRes;
        List<ToDoTask> listData = new ArrayList<>();

        listData.add(new ToDoTask("clean test", LocalDate.now(),HIGH));
        listData.add(new ToDoTask("jumps test", LocalDate.now().plusDays(1),LOW));
        listData.add(new ToDoTask("dance test", LocalDate.now().plusWeeks(1),MID));

//        when(repo.findAll()).thenReturn(listData);
        Mockito.doReturn(listData).when(repo).findAll();

        // Run Test
        listRes = service.getAllTasks();

        assertEquals(3,listRes.size());
        verify(repo, times(1)).findAll();

    }

    @Test
    void getById() {

//        ToDoTaskOutputDTO listRes;
//        List<ToDoTask> listData = new ArrayList<>();
//
//        listData.add(new ToDoTask("clean test", LocalDate.now(),HIGH));
//        listData.add(new ToDoTask("jumps test", LocalDate.now().plusDays(1),LOW));
//        listData.add(new ToDoTask("dance test", LocalDate.now().plusWeeks(1),MID));
//
//        Mockito.doReturn(listData).when(repo).findById(listData.get(1).getId());
//
//        // Run Test
//        listRes = service.getById(listData.get(1).getId());
//
//        assertEquals(listData.get(1).getId(),listRes.getId());
//        verify(repo, times(1)).findAll();
    }

    @Test
    void createTask() {
    }
}