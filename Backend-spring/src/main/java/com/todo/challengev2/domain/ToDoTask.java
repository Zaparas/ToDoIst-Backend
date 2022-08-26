package com.todo.challengev2.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class ToDoTask {

    //Enum:
    public enum PriorityType {
        LOW,
        MID,
        HIGH,
        TOP
    };

    // Variables:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //@TODO: convert to string
    private UUID id;
    private String name;
    private LocalDateTime dueDate; //@TODO: switch to LocalDate

    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    public ToDoTask(String name, LocalDateTime dueDate, PriorityType priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
