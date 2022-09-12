package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.ToDoTaskInputDTO;
import com.todo.challengev2.dto.ToDoTaskOutputDTO;
import com.todo.challengev2.model.ToDoTaskModelAssembler;
import com.todo.challengev2.services.ToDoTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public CollectionModel<EntityModel<ToDoTaskOutputDTO>> getAllTasks() {
        List<EntityModel<ToDoTaskOutputDTO>> tasks = taskService.getAllTasks().stream()
                .map(toDoTaskModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(ToDoTaskController.class).getAllTasks()).withSelfRel());

    }

    @GetMapping("/{id}")
    public EntityModel<ToDoTaskOutputDTO> getTask(@PathVariable UUID id) {
        ToDoTaskOutputDTO task = taskService.getById(id);
        return toDoTaskModelAssembler.toModel(task);
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ToDoTaskOutputDTO>> createTask(@RequestBody ToDoTaskInputDTO task) {
        EntityModel<ToDoTaskOutputDTO> entityModel = toDoTaskModelAssembler.toModel(taskService.createTask(task));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

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

    // Convert void to ResponseEntity<?>, to return status code
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
