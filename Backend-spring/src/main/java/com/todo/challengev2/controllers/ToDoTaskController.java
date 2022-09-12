package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;
import com.todo.challengev2.model.ToDoTaskModelAssembler;
import com.todo.challengev2.services.ToDoTaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ToDoTaskController {

    public final ToDoTaskServiceImpl taskService;
    private final ToDoTaskModelAssembler toDoTaskModelAssembler;

    //@TODO: CORS cross origin resource sharing - should be set to allow on the server side
    //is now allowed on the server side see SecurityConfigurer

    /** Controllers with Models */

    @Operation( summary = "Fetches all tasks currently stored and accessible in the database.", tags = {"Tasks","GetAll"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Tasks have been fetched", content = {@Content(mediaType = "application/json")}) //TODO: research media-type & json name importance?
    })
    @GetMapping
    public CollectionModel<EntityModel<ToDoTaskDTO>> getAllTasks() {
        List<EntityModel<ToDoTaskDTO>> tasks = taskService.getAllTasks().stream()
                .map(toDoTaskModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(ToDoTaskController.class).getAllTasks()).withSelfRel());

    }

    @Operation( summary = "Fetches a specific task via ID - UUID", tags = {"Tasks","GetBy"})
    @ApiResponses({
            @ApiResponse(description = "Got a specific Task using this id as a reference point", responseCode = "200", content = @Content(mediaType = "task/json")),
            @ApiResponse(description = "Did not find any Task using this id as a reference point or invalid ID", responseCode = "404", content = @Content)
    })
    @GetMapping("/{id}")
    public EntityModel<ToDoTaskDTO> getTask(@PathVariable UUID id) {
        ToDoTaskDTO task = new ToDoTaskDTO(taskService.getById(id));
        return toDoTaskModelAssembler.toModel(task);
    }


    @Operation(summary = "Stores a new task in the database", tags = {"Tasks","Create"})
    @ApiResponses({
            @ApiResponse(description = "Created a new Task", responseCode = "201", content = @Content(mediaType = "link"))
    })
    @PostMapping()
    public ResponseEntity<EntityModel<ToDoTaskDTO>> createTask(@RequestBody ToDoTaskDTO task) {
        EntityModel<ToDoTaskDTO> entityModel = toDoTaskModelAssembler.toModel(taskService.createTask(task));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Operation(summary = "Update the fields of an existing Task", tags = {"Tasks","Update"})
    @ApiResponses({
            @ApiResponse(description = "updated an existing Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to update not found", responseCode = "404", content = @Content)
            //,@ApiResponse(description = "no content", responseCode = "204", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody ToDoTaskDTO newTask, @PathVariable UUID id) {
        try {
            ToDoTaskDTO updatedTask = taskService.updateTask(newTask, id);
            EntityModel<ToDoTaskDTO> entityModel = toDoTaskModelAssembler.toModel(updatedTask);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (Exception exception) {
            // TODO: 9/9/2022 Γιατί δουλεύει εδώ και όχι στο delete ??????
            return ResponseEntity.notFound().build();
        }
    }

    // TODO: Convert void to ResponseEntity<?>, to return status code
    @Operation(summary = "delete an existing Task", tags = {"Tasks","delete"})
    @ApiResponses({
            @ApiResponse(description = "delete a Task", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to delete not found or invalid ID", responseCode = "404", content = @Content)
            //,@ApiResponse(description = "no content", responseCode = "204", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            //  return ResponseEntity.notFound().build();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
