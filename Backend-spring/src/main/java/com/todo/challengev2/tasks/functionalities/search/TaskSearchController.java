package com.todo.challengev2.tasks.functionalities.search;

import com.todo.challengev2.tasks.utils.dtos.TaskIndexDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import com.todo.challengev2.tasks.utils.models.TaskModelAssembler;
import com.todo.challengev2.tasks.functionalities.TaskService;
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

/**
 * This class implements a controller, creating an end-point for searchTask() Task service method.
 */
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskSearchController {

    /**
     * Imports Task Service to Controller.
     */
    private final TaskService taskService;

    /**
     * Imports Model Assembler to Controller.
     */
    private final TaskModelAssembler taskModelAssembler;

    /**
     * This method implements a Get Request, using searchTask() service method.
     * @param taskIndexDTO a DTO with criteria that we want to search with.
     * @return a list of TaskOutDTOs, with Entities that matching search criteria.
     */
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
