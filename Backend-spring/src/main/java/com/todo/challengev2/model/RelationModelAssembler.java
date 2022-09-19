package com.todo.challengev2.model;

import com.todo.challengev2.controllers.RelationGetByIdController;
import com.todo.challengev2.controllers.RelationListController;
import com.todo.challengev2.controllers.TaskGetByIdController;
import com.todo.challengev2.controllers.TaskListController;
import com.todo.challengev2.dto.RelationOutDTO;
import com.todo.challengev2.dto.TaskOutDTO;
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
