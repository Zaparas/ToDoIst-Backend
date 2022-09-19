package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
public class TaskInDTO {

    private String name;
    private LocalDate dueDate;
    private PriorityType priority;

    public TaskInDTO(TaskOutDTO task) {
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.priority = task.getPriority();
    }
}
