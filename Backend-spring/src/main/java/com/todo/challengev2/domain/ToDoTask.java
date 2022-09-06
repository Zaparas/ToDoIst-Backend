package com.todo.challengev2.domain;

import com.todo.challengev2.config.util.PriorityType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ToDoTask {


    // Variables:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //@TODO: convert to string
    private UUID id;
    private String name;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    public ToDoTask(String name, LocalDate dueDate, PriorityType priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
