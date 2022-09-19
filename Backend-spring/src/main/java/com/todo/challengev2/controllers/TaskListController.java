package com.todo.challengev2.controllers;

import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.model.TaskModelAssembler;
import com.todo.challengev2.services.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskListController {

    public final TaskServiceImpl taskService;
    private final TaskModelAssembler taskModelAssembler;

    @Operation( summary = "Fetches all tasks currently stored and accessible in the database.",  tags = {"Tasks",
            "GetAll"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Tasks have been fetched",  content =
                    {@Content(mediaType = "ListOfAllTasks/json")}) //TODO: research media-type & json name importance?
    })
    @GetMapping
    public CollectionModel<EntityModel<TaskOutDTO>> list() {
        List<EntityModel<TaskOutDTO>> tasks = taskService.getAllTasks().stream()
                .map(taskModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(TaskListController.class).list()).withSelfRel());

    }
}
