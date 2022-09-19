package com.todo.challengev2.controllers;

import com.todo.challengev2.services.TaskServiceImpl;
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

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskDeleteController {

    public final TaskServiceImpl taskService;

    // TODO: Convert void to ResponseEntity<?>, to return status code
    @Operation(summary = "delete an existing Task", tags = {"Tasks","delete"})
    @ApiResponses({
            @ApiResponse(description = "delete a Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to delete not found or invalid ID", responseCode = "404",  content =
    @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception){
            //  return ResponseEntity.notFound().build();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
