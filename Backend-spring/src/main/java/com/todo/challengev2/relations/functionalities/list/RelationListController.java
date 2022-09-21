package com.todo.challengev2.relations.functionalities.list;

import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.models.RelationModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/relations")
public class RelationListController {

    @Autowired
    private RelationListService relationListService;
    @Autowired
    private RelationModelAssembler relationModelAssembler;

    @Operation(summary = "Gets a list with all currently stored relations",tags = {"Relations","List"})
    @ApiResponses({
            @ApiResponse(description = "list of all relation entities returned", responseCode = "200",content =
            @Content(mediaType = "ListOfAllRelations/json"))
    })
    @GetMapping
    public CollectionModel<EntityModel<RelationFullDTO>> list() {
        List<EntityModel<RelationFullDTO>> relations = relationListService.list().stream()
                .map(relationModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(relations,
                linkTo(methodOn(RelationListController.class).list()).withSelfRel());
    }
}
