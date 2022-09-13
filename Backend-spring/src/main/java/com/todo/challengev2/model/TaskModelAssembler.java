package com.todo.challengev2.model;

import com.todo.challengev2.controllers.TaskController;
import com.todo.challengev2.dto.TaskOutDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<TaskOutDTO, EntityModel<TaskOutDTO>> {

    @Override
    public EntityModel<TaskOutDTO> toModel(TaskOutDTO task) {
        return EntityModel.of(task,
                linkTo(methodOn(TaskController.class).getTask(task.getId())).withSelfRel(),
                linkTo(methodOn(TaskController.class).getAllTasks()).withRel("tasks"));
    }
}
