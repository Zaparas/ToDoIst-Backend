package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.ToDoTaskOutputDTO;
import com.todo.challengev2.model.ToDoTaskModelAssembler;
import com.todo.challengev2.services.ToDoTaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskGetByIdController {

    public final ToDoTaskServiceImpl taskService;
    private final ToDoTaskModelAssembler toDoTaskModelAssembler;

    @Operation( summary = "Fetches a specific task via ID - UUID", tags = {"Tasks","GetBy"})
    @ApiResponses({
            @ApiResponse(description = "Got a specific Task using this id as a reference point", responseCode = "200", content = @Content(mediaType = "task/json")),
            @ApiResponse(description = "Did not find any Task using this id as a reference point or invalid ID", responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public EntityModel<ToDoTaskOutputDTO> getTask(@PathVariable UUID id) {
        ToDoTaskOutputDTO task = taskService.getById(id);
        return toDoTaskModelAssembler.toModel(task);
    }
}
