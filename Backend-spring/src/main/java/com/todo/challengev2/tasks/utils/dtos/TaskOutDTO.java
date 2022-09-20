package com.todo.challengev2.tasks.utils.dtos;

import com.todo.challengev2.tasks.utils.enums.PriorityType;
import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TaskOutDTO {

    private UUID id;
    private String name;
    private LocalDate dueDate;
    private PriorityType priority;
    private String description;
    private List<RelationOutDTO> relations = new ArrayList<>();

    public TaskOutDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
        this.description = task.getDescription();
        for (Relation relation : task.getRelations()) {
            this.relations.add(new RelationOutDTO(relation));
        }
    }
}
