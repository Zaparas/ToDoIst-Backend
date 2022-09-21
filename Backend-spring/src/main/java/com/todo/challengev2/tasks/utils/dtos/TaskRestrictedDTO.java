package com.todo.challengev2.tasks.utils.dtos;

import com.todo.challengev2.tasks.utils.enums.PriorityType;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskRestrictedDTO {

    private String name;
    private LocalDate dueDate;
    private PriorityType priority;
    private String description;
    private List<RelationRestrictedDTO> relations = new ArrayList<>();

    public TaskRestrictedDTO(TaskFullDTO taskFullDTO) {
        this.name = taskFullDTO.getName();
        this.dueDate = taskFullDTO.getDueDate();
        this.priority = taskFullDTO.getPriority();
        this.description = taskFullDTO.getDescription();
    }
}
