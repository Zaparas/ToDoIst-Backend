package com.todo.challengev2.services;

import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.convertToEntity.TaskConvertToEntityServiceImpl;
import com.todo.challengev2.tasks.functionalities.create.TaskCreateService;
import com.todo.challengev2.tasks.functionalities.create.TaskCreateServiceImpl;
import com.todo.challengev2.tasks.functionalities.get.TaskGetByIdService;
import com.todo.challengev2.tasks.functionalities.get.TaskGetByIdServiceImpl;
import com.todo.challengev2.tasks.functionalities.list.TaskListServiceImpl;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
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

import static com.todo.challengev2.tasks.utils.enums.PriorityType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {


    @InjectMocks
    TaskConvertToEntityServiceImpl convertService;
    @InjectMocks
    TaskListServiceImpl listService;
    @InjectMocks
    TaskGetByIdServiceImpl getByIdService;
    @InjectMocks
    TaskCreateServiceImpl taskCreateService;

    @Mock
    TaskRepository repo;

    @Test
    void convertToEntity() {

        Task task = new Task("jumps test", LocalDate.now().plusDays(1),LOW,"desc");
        TaskInDTO in = new TaskInDTO(new TaskOutDTO(task));

        Task res = convertService.convertToEntity(in);

        assertEquals(res.getName(),in.getName());
        assertEquals(res.getDueDate(),in.getDueDate());
        assertEquals(res.getPriority(),in.getPriority());
    }

    @Test
    void getAllTasks() {

        List<TaskOutDTO> listRes;
        List<Task> listData = new ArrayList<>();

        listData.add(new Task("clean test", LocalDate.now(),HIGH,"desc"));
        listData.add(new Task("jumps test", LocalDate.now().plusDays(1),LOW,"desc"));
        listData.add(new Task("dance test", LocalDate.now().plusWeeks(1),MID,"desc"));

        Mockito.doReturn(listData).when(repo).findAll();

        listRes = listService.list();

        assertEquals(3,listRes.size());
        verify(repo, times(1)).findAll();

    }


    @Test
    void getById_ValidId_Success() {

        Task in = new Task("jumps test", LocalDate.now().plusDays(1),LOW,"desc");
        in.setId(UUID.randomUUID());

        Mockito.doReturn(Optional.of(in)).when(repo).findById(in.getId());

        TaskOutDTO res = getByIdService.get(in.getId());

        verify(repo, times(1)).findById(in.getId());

        assertEquals(res.getId(),in.getId());
        assertEquals(res.getName(),in.getName());
        assertEquals(res.getDueDate(),in.getDueDate());
        assertEquals(res.getPriority(),in.getPriority());
    }

    @Test
    void getById_InvalidId_ResponseStatusException_NotFound() {

        Task in = new Task("jumps test", LocalDate.now().plusDays(1),LOW,"desc");
        in.setId(UUID.randomUUID());

        final UUID target = UUID.fromString(in.getId().toString().replaceAll("[^\\\\d-]","a"));

        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(repo).findById(target);

        assertThrows(ResponseStatusException.class,() ->getByIdService.get(target));
        verify(repo, times(1)).findById(target);
    }

    @Test
    void createTask() {

        // TODO fix this "Strict stubbing argument mismatch on save method save" at line with command  Mockito
        //  .doReturn(taskIn).when(repo).save(taskIn);

        Task taskIn = new Task("jumps test", LocalDate.now().plusDays(1),LOW,"desc");

        TaskInDTO pre = new TaskInDTO(new TaskOutDTO(taskIn));


//        Mockito.doReturn(taskIn).when(repo).save(taskIn); // TODO fix This creates a conflict cause it says mapping
//         already exists

        TaskOutDTO result = taskCreateService.createTask(pre);

//        verify(repo, times(1)).save(service.convertToEntity(pre)); // TODO fix Verifying times run creates an error,
//         about miss-matched input with a blank space after the object

        assertEquals(taskIn.getName(),result.getName());
        assertEquals(taskIn.getDueDate(),result.getDueDate());
        assertEquals(taskIn.getPriority(),result.getPriority());
    }
}