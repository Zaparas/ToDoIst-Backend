package com.todo.challengev2.relations.functionalities.create;

import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.models.RelationModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relations")
@RequiredArgsConstructor
public class RelationCreateController {

    private RelationCreateService relationCreateService;
    private final RelationModelAssembler relationModelAssembler;

    /**
     *  This method maps the post requests for the appropriate method create of RelationServiceImpl
     * @param relationInDTO the relationInDTO to with the field to be updated
     * @return an appropriate response code
     */
    @Operation(summary = "Stores a new relation in the database",tags = {"Relations","Create"})
    @ApiResponses({
            @ApiResponse(description = "Created a new relation and stored it with success", responseCode = "200",
                    content = @Content(mediaType = "link"))
    })
    @PostMapping
    public ResponseEntity<EntityModel<RelationFullDTO>> create(@RequestBody RelationRestrictedDTO relationInDTO) {
        EntityModel<RelationFullDTO> entityModel =
                relationModelAssembler.toModel(relationCreateService.createRelation(relationInDTO));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }
}
