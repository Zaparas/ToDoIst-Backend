package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.ToDoTask;
import com.todo.challengev2.dto.ToDoTaskDTO;
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
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<ToDoTaskDTO> getAllTasks() {
//        return taskService.getAllTasks();
//    }


//    @GetMapping("/{id}")
//    public ToDoTask getTask(@PathVariable UUID id){ return taskService.getById(id); }

//    @RequestMapping(method = RequestMethod.POST)
//    public void newTask(@RequestBody ToDoTask task){ taskService.createTask(task); }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//    public void updateTask(@RequestBody ToDoTask task, @PathVariable UUID id){ taskService.updateTask(task,id); }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    public void deleteTask(@PathVariable UUID id){ taskService.deleteTask(id); }

    /** Controllers with Models */

    @GetMapping
    public CollectionModel<EntityModel<ToDoTaskDTO>> getAllTasks() {
        List<EntityModel<ToDoTaskDTO>> tasks = taskService.getAllTasks().stream()
                .map(toDoTaskModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(ToDoTaskController.class).getAllTasks()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<ToDoTaskDTO> getTask(@PathVariable UUID id) {
        ToDoTaskDTO task = new ToDoTaskDTO(taskService.getById(id));
        return toDoTaskModelAssembler.toModel(task);
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ToDoTaskDTO>> newTask(@RequestBody ToDoTaskDTO task) {
        log.info("Inside newEmployee controller: " + task.toString());
        EntityModel<ToDoTaskDTO> entityModel = toDoTaskModelAssembler.toModel(taskService.createTask(task));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

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

    // Convert void to ResponseEntity<?>, to return status code
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        try {
            deleteTask(id);
            return ResponseEntity.ok().build();
        }
        finally {
            //  return ResponseEntity.notFound().build();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,":hello");
        }
    }
}
