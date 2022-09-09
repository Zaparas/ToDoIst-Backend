package com.todo.challengev2.model;

import com.todo.challengev2.controllers.ToDoTaskController;
import com.todo.challengev2.dto.ToDoTaskDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ToDoTaskModelAssembler implements RepresentationModelAssembler<ToDoTaskDTO, EntityModel<ToDoTaskDTO>> {

    @Override
    public EntityModel<ToDoTaskDTO> toModel(ToDoTaskDTO task) {
        return EntityModel.of(task,
                linkTo(methodOn(ToDoTaskController.class).getTask(task.getId())).withSelfRel(),
                linkTo(methodOn(ToDoTaskController.class).getAllTasks()).withRel("tasks"));
    }
}
