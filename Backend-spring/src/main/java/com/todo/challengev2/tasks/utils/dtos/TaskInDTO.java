package com.todo.challengev2.tasks.utils.dtos;

import com.todo.challengev2.tasks.utils.enums.PriorityType;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
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
public class TaskInDTO {

    private String name;
    private LocalDate dueDate;
    private PriorityType priority;
    private String description;
    private List<RelationInDTO> relations = new ArrayList<>();

    public TaskInDTO(TaskOutDTO taskOutDTO) {
        this.name = taskOutDTO.getName();
        this.dueDate = taskOutDTO.getDueDate();
        this.priority = taskOutDTO.getPriority();
        this.description = taskOutDTO.getDescription();
    }
}
