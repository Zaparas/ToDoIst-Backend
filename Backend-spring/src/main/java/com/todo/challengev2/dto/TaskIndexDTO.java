package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.PriorityType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class TaskIndexDTO {

    private String id;

    private String name;

    private LocalDate afterDate;

    private LocalDate beforeDate;

    private PriorityType priority;
}
