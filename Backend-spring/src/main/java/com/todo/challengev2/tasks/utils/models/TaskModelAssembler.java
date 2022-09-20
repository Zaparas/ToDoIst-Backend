package com.todo.challengev2.tasks.utils.models;

import com.todo.challengev2.tasks.functionalities.get.TaskGetByIdController;
import com.todo.challengev2.tasks.functionalities.list.TaskListController;
import com.todo.challengev2.tasks.utils.dtos.TaskOutDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TaskModelAssembler implements RepresentationModelAssembler<TaskOutDTO, EntityModel<TaskOutDTO>> {

    @Override
    public EntityModel<TaskOutDTO> toModel(TaskOutDTO task) {
        return EntityModel.of(task,
                linkTo(methodOn(TaskGetByIdController.class).getTask(task.getId())).withSelfRel(),
                linkTo(methodOn(TaskListController.class).list()).withRel("tasks"));
    }
}
