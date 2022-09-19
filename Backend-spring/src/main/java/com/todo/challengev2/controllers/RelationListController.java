package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.domain.Task;
import com.todo.challengev2.repositories.RelationRepository;
import com.todo.challengev2.repositories.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationListController {

    @Autowired
    private RelationRepository relationRepository;

    @Operation(summary = "Gets a list with all currently stored relations",tags = {"Relations","List"})
    @ApiResponses({
            @ApiResponse(description = "list of all relation entities returned", responseCode = "200",content =
            @Content(mediaType = "ListOfAllRelations/json"))
    })
    @GetMapping
    public List<Relation> list() {
        List<Relation> result = relationRepository.findAll();
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
}
