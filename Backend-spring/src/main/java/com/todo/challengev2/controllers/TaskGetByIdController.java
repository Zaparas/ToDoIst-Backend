package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.model.TaskModelAssembler;
import com.todo.challengev2.services.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * This class implements a controller, creating an end-point for GetById Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskGetByIdController {

    /**
     * Imports Task Service to Controller.
     */
    public final TaskServiceImpl taskService;
    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskModelAssembler taskModelAssembler;

    /**
     * This method implements a Get Request, using get() service method.
     * @param id, the requested ID of the Entity that we want to fetch.
     * @return a TaskOutDTO of the Entity (if is found).
     */
    @Operation( summary = "Fetches a specific task via ID - UUID", tags = {"Tasks","GetBy"})
    @ApiResponses({
            @ApiResponse(description = "Got a specific Task using this id as a reference point",   responseCode =
                    "200", content = @Content(mediaType = "task/json")),
            @ApiResponse(description = "Did not find any Task using this id as a reference point or invalid ID",
                    responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public EntityModel<TaskOutDTO> getTask(@PathVariable UUID id) {
        TaskOutDTO result = taskService.getById(id);
        if (result==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Relation with id: <" + id + "> not found. Get by id failed");
        return taskModelAssembler.toModel(result);
    }
}
