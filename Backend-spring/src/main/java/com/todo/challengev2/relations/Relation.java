package com.todo.challengev2.relations;

import com.todo.challengev2.relations.utils.enums.RelationType;
import com.todo.challengev2.tasks.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private RelationType relationType;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    public Relation(RelationType relationType, Task task) {
        this.relationType = relationType;
        this.task = task;
    }
}
