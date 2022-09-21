package com.todo.challengev2.relations.functionalities.get;

import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.models.RelationModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relations")
public class RelationGetByIdController {

    private final RelationGetByIdService relationGetByIdService;

    private final RelationModelAssembler relationModelAssembler;

    /**
     * This method maps the url "/relations/{id} to the method get of RelationServiceImpl with the purpose of getting
     * a relation entity with the specific id.
     * @param id the id to search for
     * @return an appropriate response code
     */
    @Operation(summary = "Gets a relation with the selected ID",tags = {"Relations","GetBy"})
    @ApiResponses({
            @ApiResponse(description = "Got the target relation", responseCode = "200",content =
            @Content(mediaType = "relation/json")),
            @ApiResponse(description = "Relation with target id not found", responseCode = "404",content =
            @Content)
    })
    @GetMapping("/{id}")
    public EntityModel<RelationFullDTO> get(@PathVariable UUID id) {

        RelationFullDTO result = relationGetByIdService.get(id);
        if (result ==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Relation with id: <" + id + "> not found. Get by id failed");
        return relationModelAssembler.toModel(result);
    }
}
