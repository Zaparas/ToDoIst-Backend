package com.todo.challengev2.domain;

import com.todo.challengev2.config.util.PriorityType;
import com.todo.challengev2.config.util.RelationType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
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
    private String description;
    @OneToMany(mappedBy = "task", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    List<Relation> relations = new ArrayList<>();

    public Task(String name, LocalDate dueDate, PriorityType priority, String description, List<Relation> relations) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
        this.relations = relations;
    }

    public Task(String name, LocalDate dueDate, PriorityType priority, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
    }
}
