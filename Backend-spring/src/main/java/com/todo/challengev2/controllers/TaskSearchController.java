package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskIndexDTO;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.model.TaskModelAssembler;
import com.todo.challengev2.repositories.TaskRepository;
import com.todo.challengev2.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskSearchController {

    private final TaskService taskService;
    private final TaskModelAssembler taskModelAssembler;

    @Operation( summary = "Fetches tasks, according user's search", tags = {"Tasks","Search"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results have been fetched", content =
                    {@Content(mediaType = "SearchResults/json")})
    })
    @GetMapping("/search")
    public CollectionModel<EntityModel<TaskOutDTO>> search(@RequestBody TaskIndexDTO taskIndexDTO) {
        List<EntityModel<TaskOutDTO>> tasks = taskService.searchTask(taskIndexDTO).stream()
                .map(taskModelAssembler :: toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(TaskSearchController.class).search(taskIndexDTO)).withSelfRel());
    }
}
