package com.todo.challengev2.model;

import com.todo.challengev2.controllers.TaskGetByIdController;
import com.todo.challengev2.controllers.TaskListController;
import com.todo.challengev2.dto.ToDoTaskOutputDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ToDoTaskModelAssembler implements RepresentationModelAssembler<ToDoTaskOutputDTO, EntityModel<ToDoTaskOutputDTO>> {

    @Override
    public EntityModel<ToDoTaskOutputDTO> toModel(ToDoTaskOutputDTO task) {
        return EntityModel.of(task,
                linkTo(methodOn(TaskGetByIdController.class).getTask(task.getId())).withSelfRel(),
                linkTo(methodOn(TaskListController.class).list()).withRel("tasks"));
    }
}
