package com.todo.challengev2.tasks.utils.dtos;

import com.todo.challengev2.tasks.utils.enums.PriorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TaskIndexDTO {

    private String id;

    private String name;

    private LocalDate afterDate;

    private LocalDate beforeDate;

    private PriorityType priority;
}
