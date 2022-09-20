package com.todo.challengev2.relations.functionalities.delete;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relations")
public class RelationDeleteController {

    @Autowired
    private RelationDeleteService relationDeleteService;

    /**
     *  This method maps the delete requests to the delete method of RelationServiceImpl.
     * @param id the id to be passed to the method as the target of the deletion
     * @return an appropriate response code, according to the result of the operation.
     */
    @Operation(summary = "Deletes the relation with the selected ID",tags = {"Relations","GetBy"})
    @ApiResponses({
            @ApiResponse(description = "Deleted the target relation", responseCode = "200",content =
            @Content),
            @ApiResponse(description = "Relation with target id not found", responseCode = "404",content =
            @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try{
            relationDeleteService.deleteRelation(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Target relation of delete with id <" + id + "> not found.");
        }
    }
}
