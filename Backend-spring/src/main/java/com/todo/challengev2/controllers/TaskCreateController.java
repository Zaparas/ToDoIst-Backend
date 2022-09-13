package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.ToDoTaskInputDTO;
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
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskCreateController {

    public final ToDoTaskServiceImpl taskService;
    private final ToDoTaskModelAssembler toDoTaskModelAssembler;

    @Operation(summary = "Stores a new task in the database", tags = {"Tasks","Create"})
    @ApiResponses({
            @ApiResponse(description = "Created a new Task", responseCode = "201", content = @Content(mediaType = "link"))
    })
    @PostMapping()
    public ResponseEntity<EntityModel<ToDoTaskOutputDTO>> createTask(@RequestBody ToDoTaskInputDTO task) {
        EntityModel<ToDoTaskOutputDTO> entityModel = toDoTaskModelAssembler.toModel(taskService.createTask(task));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
