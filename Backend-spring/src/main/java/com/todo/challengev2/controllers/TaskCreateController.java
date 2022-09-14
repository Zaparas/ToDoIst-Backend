package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.TaskInDTO;
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
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskCreateController {

    public final TaskServiceImpl taskService;
    private final TaskModelAssembler taskModelAssembler;

    @Operation(summary = "Stores a new task in the database", tags = {"Tasks","Create"})
    @ApiResponses({
            @ApiResponse(description = "Created a new Task", responseCode = "201", content = @Content(mediaType = "link"))
    })
    @PostMapping()
    public ResponseEntity<EntityModel<TaskOutDTO>> createTask(@RequestBody TaskInDTO task) {
        EntityModel<TaskOutDTO> entityModel = taskModelAssembler.toModel(taskService.createTask(task));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}