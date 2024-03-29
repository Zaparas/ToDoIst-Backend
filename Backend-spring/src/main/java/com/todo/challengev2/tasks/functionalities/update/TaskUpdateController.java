package com.todo.challengev2.tasks.functionalities.update;

import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import com.todo.challengev2.tasks.utils.models.TaskModelAssembler;
import com.todo.challengev2.tasks.functionalities.TaskServiceImpl;
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

/**
 * This class implements a controller, creating an end-point for updateTask() Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskUpdateController {

    /**
     * Imports Task Service to Controller.
     */
    public final TaskServiceImpl taskService;

    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskModelAssembler taskModelAssembler;

    /**
     * This method implements a Put Request, using updateTask() service method.
     * @param newTask, a TaskInDTO with the data that we want to update in the database.
     * @param id, the requested ID of the Entity that we want to update.
     * @return a TaskOutDTO with the updated Entity.
     */
    @Operation(summary = "Update the fields of an existing Task", tags = {"Tasks","Update"})
    @ApiResponses({
            @ApiResponse(description = "updated an existing Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to update not found", responseCode = "404", content = @Content)
            //,@ApiResponse(description = "no content", responseCode = "204", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskInDTO newTask, @PathVariable UUID id) {
        try {
            TaskOutDTO updatedTask = taskService.updateTask(newTask, id);
            EntityModel<TaskOutDTO> entityModel = taskModelAssembler.toModel(updatedTask);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (Exception exception) {
            // TODO: 9/9/2022 Γιατί δουλεύει εδώ και όχι στο delete ??????
            return ResponseEntity.notFound().build();
        }
    }
}
