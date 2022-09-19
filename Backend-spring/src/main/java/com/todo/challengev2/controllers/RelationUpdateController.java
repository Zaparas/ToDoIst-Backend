package com.todo.challengev2.controllers;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.dto.RelationInDTO;
import com.todo.challengev2.dto.RelationOutDTO;
import com.todo.challengev2.dto.TaskOutDTO;
import com.todo.challengev2.model.RelationModelAssembler;
import com.todo.challengev2.repositories.RelationRepository;
import com.todo.challengev2.services.RelationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relations")
@RequiredArgsConstructor
public class RelationUpdateController {

    private RelationServiceImpl relationService;
    private RelationModelAssembler relationModelAssembler;

    /**
     * This method maps the put requests to the appropriate RelationServiceImpl, the method update
     * @param relationInDTO is a DTO with the new value to be entered to the selected object
     * @param id the target object, the oen to be overwritten
     * @return an appropriate response code
     */
    @Operation(summary = "Update the fields of an existing Relation", tags = {"Relations","Update"})
    @ApiResponses({
            @ApiResponse(description = "updated an existing Relation", responseCode = "200", content = @Content)
            ,@ApiResponse(description = "task to update not found", responseCode = "404", content = @Content)
            //,@ApiResponse(description = "no content", responseCode = "204", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody RelationInDTO relationInDTO, @PathVariable UUID id) {
        try {
            RelationOutDTO updatedRelation = relationService.update(relationInDTO, id);
            EntityModel<RelationOutDTO> entityModel = relationModelAssembler.toModel(updatedRelation);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (Exception exception) {
            // TODO: 9/9/2022 Γιατί δουλεύει εδώ και όχι στο delete ??????
            return ResponseEntity.notFound().build();
        }
    }
}
