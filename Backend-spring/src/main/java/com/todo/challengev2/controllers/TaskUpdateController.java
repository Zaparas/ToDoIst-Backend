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

import java.util.UUID;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskUpdateController {

    public final ToDoTaskServiceImpl taskService;
    private final ToDoTaskModelAssembler toDoTaskModelAssembler;

    @Operation(summary = "Update the fields of an existing Task", tags = {"Tasks","Update"})
    @ApiResponses({
            @ApiResponse(description = "updated an existing Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to update not found", responseCode = "404", content = @Content)
            //,@ApiResponse(description = "no content", responseCode = "204", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody ToDoTaskInputDTO newTask, @PathVariable UUID id) {
        try {
            ToDoTaskOutputDTO updatedTask = taskService.updateTask(newTask, id);
            EntityModel<ToDoTaskOutputDTO> entityModel = toDoTaskModelAssembler.toModel(updatedTask);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (Exception exception) {
            // TODO: 9/9/2022 Γιατί δουλεύει εδώ και όχι στο delete ??????
            return ResponseEntity.notFound().build();
        }
    }
}
