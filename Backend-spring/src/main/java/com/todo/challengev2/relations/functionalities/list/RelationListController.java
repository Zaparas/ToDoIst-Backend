package com.todo.challengev2.relations.functionalities.list;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
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
    private RelationListService relationListService;

    @Operation(summary = "Gets a list with all currently stored relations",tags = {"Relations","List"})
    @ApiResponses({
            @ApiResponse(description = "list of all relation entities returned", responseCode = "200",content =
            @Content(mediaType = "ListOfAllRelations/json"))
    })
    @GetMapping
    public List<RelationOutDTO> list() {
        return relationListService.list();
    }
}
