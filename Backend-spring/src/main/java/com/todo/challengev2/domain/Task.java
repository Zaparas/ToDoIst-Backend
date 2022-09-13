package com.todo.challengev2.domain;

import com.todo.challengev2.config.util.PriorityType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Task {


    // Variables:
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //@TODO: convert to string
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    public Task(String name, LocalDate dueDate, PriorityType priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
