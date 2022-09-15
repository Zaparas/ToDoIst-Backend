package com.todo.challengev2.services;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskInDTO;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

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

        Task task = new Task("jumps test", LocalDate.now().plusDays(1),LOW);
        TaskInDTO in = new TaskInDTO(new TaskOutDTO(task));

        Task res = service.convertToEntity(in);

        assertEquals(res.getName(),in.getName());
        assertEquals(res.getDueDate(),in.getDueDate());
        assertEquals(res.getPriority(),in.getPriority());
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
    void getById_ValidId_Success() {

        Task in = new Task("jumps test", LocalDate.now().plusDays(1),LOW);
        in.setId(UUID.randomUUID());

        Mockito.doReturn(Optional.of(in)).when(repo).findById(in.getId());

        TaskOutDTO res = service.getById(in.getId());

        verify(repo, times(1)).findById(in.getId());

        assertEquals(res.getId(),in.getId());
        assertEquals(res.getName(),in.getName());
        assertEquals(res.getDueDate(),in.getDueDate());
        assertEquals(res.getPriority(),in.getPriority());
    }

    @Test
    void getById_InvalidId_ResponseStatusException_NotFound() {

        Task in = new Task("jumps test", LocalDate.now().plusDays(1),LOW);
        in.setId(UUID.randomUUID());

        final UUID target = UUID.fromString(in.getId().toString().replaceAll("[^\\\\d-]","a"));

        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(repo).findById(target);

        assertThrows(ResponseStatusException.class,() ->service.getById(target));
        verify(repo, times(1)).findById(target);
    }

    @Test
    void createTask() {

        // TODO fix this "Strict stubbing argument mismatch on save method save" at line with command Mockito.doReturn(taskIn).when(repo).save(taskIn);

        Task taskIn = new Task("jumps test", LocalDate.now().plusDays(1),LOW);

        TaskInDTO pre = new TaskInDTO(new TaskOutDTO(taskIn));


        Mockito.doReturn(taskIn).when(repo).save(taskIn);

//        TaskOutDTO result = service.createTask(pre);

        verify(repo, times(1)).save(service.convertToEntity(pre));

////        assertEquals(out.getId(),res.getId());
//        assertEquals(times(1).)
//        assertEquals(taskIn.getName(),result.getName());
//        assertEquals(taskIn.getDueDate(),result.getDueDate());
//        assertEquals(taskIn.getPriority(),result.getPriority());
    }
}