package com.todo.challengev2.tasks.functionalities.delete;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

/**
 * This class implements a controller, creating an end-point for Delete Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskDeleteController {

    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskDeleteService taskDeleteService;

    /**
     * This method implements a Delete Request, using deleteTask() service method.
     * @param id, the requested ID of the Entity that we want to delete from the database.
     * @return an Response Entity, depends on whether deletion was successful or not.
     */
    @Operation(summary = "delete an existing Task", tags = {"Tasks","delete"})
    @ApiResponses({
            @ApiResponse(description = "delete a Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to delete not found or invalid ID", responseCode = "404",  content =
    @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        try {
            taskDeleteService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
