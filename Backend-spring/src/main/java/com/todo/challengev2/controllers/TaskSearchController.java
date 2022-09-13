package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskSearchController {

    private final TaskRepository taskRepository;

    @GetMapping("/search")
    public List<TaskOutDTO> search(@RequestParam("args") String args) {
        List<TaskOutDTO> list = new ArrayList<>();
        for (Task task : taskRepository.search(args)) {
            list.add(new TaskOutDTO(task));
        }
        return list;
    }
}
