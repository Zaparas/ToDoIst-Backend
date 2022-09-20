package com.todo.challengev2.tasks;
import com.todo.challengev2.tasks.utils.enums.PriorityType;
import com.todo.challengev2.relations.Relation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Relation> relations = new ArrayList<>();
    public Task(String name, LocalDate dueDate, PriorityType priority, String description, List<Relation> relations) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
        this.relations.addAll(relations);
    }

    public Task(String name, LocalDate dueDate, PriorityType priority, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
    }
}