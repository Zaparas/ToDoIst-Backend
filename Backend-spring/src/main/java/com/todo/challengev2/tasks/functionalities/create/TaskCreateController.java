package com.todo.challengev2.tasks.functionalities.create;

import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import com.todo.challengev2.tasks.utils.models.TaskModelAssembler;
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

/**
 * This class implements a controller, creating an end-point for Create Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskCreateController {

    /**
     * Imports Task Service to Controller.
     */
    public final TaskCreateService taskCreateService;
    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskModelAssembler taskModelAssembler;

    /**
     * This method implements a Post Request, using createTask() service method.
     * @param taskInDTO, a DTO including the fields of Task that we want to save on our database.
     * @return a new TaskOutDTO, of created Entity.
     */
    @Operation(summary = "Stores a new taskInDTO in the database", tags = {"Tasks","Create"})
    @ApiResponses({
            @ApiResponse(description = "Created a new Task", responseCode = "201", content =  @Content(mediaType =
                    "link"))
    })
    @PostMapping()
    public ResponseEntity<EntityModel<TaskOutDTO>> createTask(@RequestBody TaskInDTO taskInDTO) {
        EntityModel<TaskOutDTO> entityModel = taskModelAssembler.toModel(taskCreateService.createTask(taskInDTO));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
