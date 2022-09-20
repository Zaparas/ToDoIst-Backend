package com.todo.challengev2.relations.utils.models;

import com.todo.challengev2.relations.functionalities.get.RelationGetByIdController;
import com.todo.challengev2.relations.functionalities.list.RelationListController;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RelationModelAssembler implements RepresentationModelAssembler<RelationOutDTO, EntityModel<RelationOutDTO>> {

    @Override
    public EntityModel<RelationOutDTO> toModel(RelationOutDTO relation) {
        return EntityModel.of(relation,
                linkTo(methodOn(RelationGetByIdController.class).get(relation.getId())).withSelfRel(),
                linkTo(methodOn(RelationListController.class).list()).withRel("relations"));
    }
}
