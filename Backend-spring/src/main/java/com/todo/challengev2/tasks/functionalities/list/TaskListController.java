package com.todo.challengev2.tasks.functionalities.list;

import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import com.todo.challengev2.tasks.utils.models.TaskModelAssembler;
import com.todo.challengev2.tasks.functionalities.TaskServiceImpl;
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

/**
 * This class implements a controller, creating an end-point for list() Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskListController {

    /**
     * Imports Task Service to Controller.
     */
    public final TaskServiceImpl taskService;
    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskModelAssembler taskModelAssembler;

    /**
     * This method implements a Get Request, using list() service method.
     * @return a list of TaskOutDTOs, of stored tasks in the database.
     */
    @Operation( summary = "Fetches all tasks currently stored and accessible in the database.",  tags = {"Tasks",
            "GetAll"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Tasks have been fetched",  content =
                    {@Content(mediaType = "ListOfAllTasks/json")})
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
